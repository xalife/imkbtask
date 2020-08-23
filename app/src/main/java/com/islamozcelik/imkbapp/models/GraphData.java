package com.islamozcelik.imkbapp.models;

public class GraphData {
    private int day;
    private double value;

    public GraphData(int day, double value) {
        this.day = day;
        this.value = value;
    }
    public GraphData(){

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
