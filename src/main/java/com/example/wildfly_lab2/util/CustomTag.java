package com.example.wildfly_lab2.util;

import com.example.wildfly_lab2.model.Attempt;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class CustomTag extends SimpleTagSupport {
    private Boolean x;
    private Boolean y;
    private Boolean r;
    private List<Attempt> collection;

    public Boolean getX() {
        return x;
    }

    public void setX(Boolean x) {
        this.x = x;
    }

    public Boolean getY() {
        return y;
    }

    public void setY(Boolean y) {
        this.y = y;
    }

    public Boolean getR() {
        return r;
    }

    public void setR(Boolean r) {
        this.r = r;
    }

    public List<Attempt> getCollection() {
        return collection;
    }

    public void setCollection(List<Attempt> collection) {
        this.collection = collection;
    }

    @Override
    public void doTag() throws IOException, JspException {
        StringBuilder stringBuilder = new StringBuilder();
        if (collection.size() == 0) return;
        if (x != null && x) {
            stringBuilder.append("xMin: ").append(collection.stream().mapToDouble(Attempt::getX).min().getAsDouble()).append(" xMax: ").append(collection.stream().mapToDouble(Attempt::getX).max().getAsDouble()).append(" xMean: ").append(collection.stream().mapToDouble(Attempt::getX).average().getAsDouble()).append('\n');
        }
        if (y != null && y) {

            stringBuilder.append("yMin: ").append(collection.stream().mapToDouble(Attempt::getY).min().getAsDouble()).append(" yMax: ").append(collection.stream().mapToDouble(Attempt::getY).max().getAsDouble()).append(" yMean: ").append(collection.stream().mapToDouble(Attempt::getY).average().getAsDouble()).append('\n');
        }
        if (r != null && r) {
            stringBuilder.append("rMin: ").append(collection.stream().mapToDouble(Attempt::getR).min().getAsDouble()).append(" rMax: ").append(collection.stream().mapToDouble(Attempt::getR).max().getAsDouble()).append(" rMean: ").append(collection.stream().mapToDouble(Attempt::getR).average().getAsDouble()).append('\n');
        }
        getJspContext().getOut().write(stringBuilder.toString());
    }

}