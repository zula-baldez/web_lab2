package com.example.wildfly_lab2.controller;

import com.example.wildfly_lab2.model.TableBean;
import com.example.wildfly_lab2.util.RequestParamsConfigurer;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
    private RequestParamsConfigurer requestParamsConfigure = new RequestParamsConfigurer();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TableBean tableBean = (TableBean) request.getAttribute("bean");
        long startTimeInNano = System.nanoTime();
        Date startTime = new Date();
        String error = checkRequest(request, response);
        RequestDispatcher errDisp = getServletContext().getNamedDispatcher("ServletController");

        if(error != null) {
            request.setAttribute("error", error);

            errDisp.forward(request, response);
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
            request.setAttribute("error", "please enter a number");
            errDisp.forward(request, response);
            return;
        }

        boolean hit = checkHit(x, y, r);
        tableBean.addAttempt(Double.parseDouble(request.getParameter("x")),
                Double.parseDouble(request.getParameter("y")),
                Double.parseDouble(request.getParameter("r")),
                hit,
                Long.toString((System.nanoTime() - startTimeInNano)/1000),
                simpleDateFormat.format(startTime));
        requestParamsConfigure.configParams(tableBean, request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./result.jsp");
        requestDispatcher.forward(request, response);


    }

    public boolean checkHit(double x, double y, double r) {
        //y = r - 2x
        if (x >= 0 && y <= r - 2*x && y >= 0) return true;
        if (x >= -r / 2 && x <= 0 && y >= 0 && y <= r) return true;
        if (x <= 0 && y <= 0 && x*x + y*y <= r*r/4) return true;
        return  false;
    }
    public String checkRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("r") == null) {

            return "enter R first";
        }
        if (request.getParameter("x") == null) {
            return "enter X first";
        }
        if (request.getParameter("y") == null) {

            return "enter Y first";
        }
        return null;
    }

}