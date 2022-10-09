package com.example.wildfly_lab2.controller;

import com.example.wildfly_lab2.model.TableBean;
import com.example.wildfly_lab2.util.RequestParamsConfigurer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ServletAreaCheck", value = "/hit_handler")
public class ServletAreaCheck extends HttpServlet {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    @EJB
    private TableBean tableBean;
    @EJB
    private RequestParamsConfigurer requestParamsConfigurer;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long startTimeInNano = System.nanoTime();
        Date startTime = new Date();
        if(!checkRequest(request, response)) {
            return;
        }
        double x;
        double y;
        double r;
        try {
            x = Double.parseDouble(request.getParameter("x"));

            y = Double.parseDouble(request.getParameter("y"));

            r = Double.parseDouble(request.getParameter("r"));

        } catch (NumberFormatException e) {
            response.getWriter().write("please enter a number");
            return;
        }

        boolean hit = checkHit(x, y, r);
        tableBean.addAttempt(Double.parseDouble(request.getParameter("x")),
                Double.parseDouble(request.getParameter("y")),
                Double.parseDouble(request.getParameter("r")),
                hit,
                Long.toString((System.nanoTime() - startTimeInNano)/1000),
                simpleDateFormat.format(startTime));
        requestParamsConfigurer.configParams(tableBean, request);
        response.sendRedirect(getServletContext().getContextPath() + "/?show_res=1");




    }

    public boolean checkHit(double x, double y, double r) {
        //y = r - 2x
        if (x >= 0 && y <= r - 2*x && y >= 0) return true;
        if (x >= -r / 2 && x <= 0 && y >= 0 && y <= r) return true;
        if (x <= 0 && y <= 0 && x*x + y*y <= r*r) return true;
        return  false;
    }
    public boolean checkRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("r") == null) {
            response.getWriter().write("Enter r first");
            return false;
        }
        if (request.getParameter("x") == null) {
            response.getWriter().write("Enter x first");
            return false;
        }
        if (request.getParameter("y") == null) {
            response.getWriter().write("Enter y first");
            return false;
        }
        return true;
    }

}