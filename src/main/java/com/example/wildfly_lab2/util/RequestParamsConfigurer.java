package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.TableBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class RequestParamsConfigurer {
    @EJB
    private CoordinatesJSONParser coordinatesJSONParser;
    @EJB
    private TableHandler tableHandler;

    public void configParams(TableBean bean, HttpServletRequest request) {
        tableHandler.addTable(bean, request);
        coordinatesJSONParser.addJSONCoordinates(bean, request);
        request.setAttribute("bean", bean);
    }

}
