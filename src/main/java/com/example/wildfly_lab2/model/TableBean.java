package com.example.wildfly_lab2.model;

import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Singleton
public class TableBean implements Serializable {
    private final List<Attempt> attempts = new ArrayList<>();

    public void addAttempt(double x, double y, double r, boolean hit, String workTime, String startTime) {
        attempts.add(new Attempt(x, y, r, hit, workTime, startTime));


    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableBean tableBean = (TableBean) o;
        return Objects.equals(attempts, tableBean.attempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempts);
    }
}
