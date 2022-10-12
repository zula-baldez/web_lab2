package com.example.wildfly_lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Attempt {
    private final Double x;
    private final Double y;
    private final Double r;
    private final Boolean hit;
    private final String workTime;
    private final String startTime;

    public Attempt(Double x, Double y, Double r, Boolean hit, String workTime, String startTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.workTime = workTime;
        this.startTime = startTime;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public Boolean getHit() {
        return hit;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return Objects.equals(x, attempt.x) && Objects.equals(y, attempt.y) && Objects.equals(r, attempt.r) && Objects.equals(hit, attempt.hit) && Objects.equals(workTime, attempt.workTime) && Objects.equals(startTime, attempt.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, hit, workTime, startTime);
    }

}
