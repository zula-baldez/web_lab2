package com.example.wildfly_lab2.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableBean implements Serializable {
    private final List<Double> x = new ArrayList<>();
    private final List<Double> y = new ArrayList<>();
    private final List<Double> r = new ArrayList<>();
    private final List<Boolean> hit = new ArrayList<>() ;
    private final List<String> workTime = new ArrayList<>();
    private final List<String> startTime = new ArrayList<>();
    public TableBean () {

    }
    public void addAttempt(double x, double y, double r, boolean hit, String workTime, String startTime) {
        this.x.add(x);
        this.y.add(y);
        this.r.add(r);
        this.hit.add(hit);
        this.workTime.add(workTime);
        this.startTime.add(startTime);



    }
    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }

    public List<Double> getR() {
        return r;
    }

    public List<Boolean> getHit() {
        return hit;
    }

    public List<String> getWorkTime() {
        return workTime;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableBean tableBean = (TableBean) o;
        return Objects.equals(x, tableBean.x) && Objects.equals(y, tableBean.y) && Objects.equals(r, tableBean.r) && Objects.equals(hit, tableBean.hit) && Objects.equals(workTime, tableBean.workTime) && Objects.equals(startTime, tableBean.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, hit, workTime, startTime);
    }

    @Override
    public String toString() {
        return "TableBean{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", workTime=" + workTime +
                ", startTime=" + startTime +
                '}';
    }
}
