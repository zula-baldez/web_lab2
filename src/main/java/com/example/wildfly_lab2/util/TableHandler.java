package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.TableBean;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class TableHandler {
    public void addTable(TableBean tableBean, HttpServletRequest request) {

        StringBuilder stringBuilder = new StringBuilder();
        if (tableBean != null)
            for (int i = 0; i < tableBean.getAttempts().size(); i++) {
                stringBuilder.append("<tr>");
                stringBuilder.append("<td>").append(i + 1).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getX()).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getY()).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getR()).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getHit()).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getWorkTime()).append("</td>");
                stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getStartTime()).append("</td>");
                stringBuilder.append("</tr>");
            }
        request.setAttribute("table", stringBuilder.toString());
    }
}
