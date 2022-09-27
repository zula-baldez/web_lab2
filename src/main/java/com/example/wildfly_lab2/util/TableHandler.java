package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.TableBean;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class TableHandler {
    public void addTable(TableBean tableBean, HttpServletRequest request) {

        StringBuilder stringBuilder = new StringBuilder();
        if (tableBean != null)
            for (int i = 0; i < tableBean.getHit().size(); i++) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>").append(i + 1).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getX().get(i)).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getY().get(i)).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getR().get(i)).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getHit().get(i)).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getWorkTime().get(i)).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getStartTime().get(i)).append("</td>");
                stringBuilder.append("</tr>");
            }
        request.setAttribute("table", stringBuilder.toString());
    }
}
