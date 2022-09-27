package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.TableBean;
import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CoordinatesJSONParser {
    public void addJSONCoordinates(TableBean bean, HttpServletRequest request) {


        if(bean == null || bean.getX() == null || bean.getY() == null || bean.getR() == null) {
            request.setAttribute("xList", "[]");
            request.setAttribute("yList", "[]");
            request.setAttribute("rList", "[]");
        } else {
            request.setAttribute("xList", new Gson().toJson(bean.getX()));
            request.setAttribute("yList", new Gson().toJson(bean.getY()));
            request.setAttribute("rList", new Gson().toJson(bean.getR()));

        }

    }
}
