package com.example.wildfly_lab2.model;

import com.example.wildfly_lab2.beans.TableBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Validator?
@WebServlet(name = "ServletAreaCheck", value = "/hit_handler")
public class ServletAreaCheck extends HttpServlet {

    private final TableBean tableBean = new TableBean();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long startTimeInNano = System.nanoTime();
        Date startTime = new Date();
        if (request.getParameter("r") == null) {
            response.getWriter().write("Enter r first");
            return;
        }
        if (request.getParameter("x") == null) {
            response.getWriter().write("Enter x first");
            return;
        }
        if (request.getParameter("y") == null) {
            response.getWriter().write("Enter y first");
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        tableBean.addAttempt(Double.parseDouble(request.getParameter("x")),
                Double.parseDouble(request.getParameter("y")),
                Double.parseDouble(request.getParameter("r")),
                hit,
                Long.toString((System.nanoTime() - startTimeInNano)/1000),
                simpleDateFormat.format(startTime));
        request.setAttribute("bean", tableBean);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/page.jsp");
        requestDispatcher.forward(request, response);
    }

    public boolean checkHit(double x, double y, double r) {
        //y = r - 2x
        if (x >= 0 && y <= r - 2*x && y >= 0) return true;
        if (x >= -r / 2 && x <= 0 && y >= 0 && y <= r) return true;
        if (x <= 0 && y <= 0 && x*x + y*y <= r*r) return true;
        return  false;
    }

}


