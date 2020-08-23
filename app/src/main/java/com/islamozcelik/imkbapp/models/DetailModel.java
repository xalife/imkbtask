package com.islamozcelik.imkbapp.models;

import java.util.ArrayList;

public class DetailModel {
    private boolean isDown;
    private boolean isUp;
    private double bid;
    private double change;
    private int count;
    private double difference;
    private double offer;
    private double highest;
    private double lowest;
    private double minumum;
    private double maximum;
    private double price;
    private double volume;
    private String symbol;
    private ArrayList<GraphData> graphdatas;

    public DetailModel(boolean isDown, boolean isUp, double bid, double change, int count, double difference, double offer, double highest, double lowest, double minumum, double maximum, double price, double volume, String symbol, ArrayList<GraphData> graphData) {
        this.isDown = isDown;
        this.isUp = isUp;
        this.bid = bid;
        this.change = change;
        this.count = count;
        this.difference = difference;
        this.offer = offer;
        this.highest = highest;
        this.lowest = lowest;
        this.minumum = minumum;
        this.maximum = maximum;
        this.price = price;
        this.volume = volume;
        this.symbol = symbol;
        this.graphdatas = graphData;
    }
    public DetailModel(){

    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }

    public double getLowest() {
        return lowest;
    }

    public void setLowest(double lowest) {
        this.lowest = lowest;
    }

    public double getMinumum() {
        return minumum;
    }

    public void setMinumum(double minumum) {
        this.minumum = minumum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public ArrayList<GraphData> getGraphData() {
        return graphdatas;
    }

    public void setGraphData(ArrayList<GraphData> graphData) {
        this.graphdatas = graphData;
    }
}
