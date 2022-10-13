package com.example.wildfly_lab2.controller;

import com.example.wildfly_lab2.model.TableBean;
import com.example.wildfly_lab2.util.RequestParamsConfigurer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletController", value = "")
public class ServletController extends HttpServlet {
    private final RequestParamsConfigurer requestParamsConfigurer = new RequestParamsConfigurer();
    private final TableBean tableBean = new TableBean();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher;
        requestParamsConfigurer.configParams(tableBean, request);
        if (request.getParameter("r") != null || request.getParameter("x") != null || request.getParameter("y") != null) {
            requestDispatcher = getServletContext().getNamedDispatcher("ServletAreaCheck");


        } else {
            requestDispatcher = getServletContext().getRequestDispatcher("/page.jsp");

        }
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/page.jsp");
        requestDispatcher.forward(request, response);
    }


}
