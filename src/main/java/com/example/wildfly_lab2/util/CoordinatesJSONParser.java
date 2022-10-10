package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.Attempt;
import com.example.wildfly_lab2.model.TableBean;
import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CoordinatesJSONParser {
    public void addJSONCoordinates(TableBean bean, HttpServletRequest request) {


        if(bean == null || bean.getAttempts().size() == 0) {
            request.setAttribute("xList", "[]");
            request.setAttribute("yList", "[]");
            request.setAttribute("rList", "[]");
        } else {
            List<Double> xList = bean.getAttempts().stream().map(Attempt::getX).collect(Collectors.toList());
            List<Double> yList = bean.getAttempts().stream().map(Attempt::getY).collect(Collectors.toList());
            List<Double> rList = bean.getAttempts().stream().map(Attempt::getR).collect(Collectors.toList());

            request.setAttribute("xList", new Gson().toJson(xList));
            request.setAttribute("yList", new Gson().toJson(yList));
            request.setAttribute("rList", new Gson().toJson(rList));

        }

    }
}
