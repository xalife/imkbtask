package com.islamozcelik.imkbapp.models;

public class Stocks  {
    private Integer id;
    private Boolean isDown;
    private Boolean isUp;
    private Double bid;
    private Double difference;
    private Double offer;
    private Double price;
    private Double volume;
    private String symbol;

    public Stocks(Integer id, Boolean isDown, Boolean isUp, Double bid, Double difference, Double offer, Double price, Double volume, String symbol) {
        this.id = id;
        this.isDown = isDown;
        this.isUp = isUp;
        this.bid = bid;
        this.difference = difference;
        this.offer = offer;
        this.price = price;
        this.volume = volume;
        this.symbol = symbol;
    }
    public Stocks(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDown() {
        return isDown;
    }

    public void setDown(Boolean down) {
        isDown = down;
    }

    public Boolean getUp() {
        return isUp;
    }

    public void setUp(Boolean up) {
        isUp = up;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
