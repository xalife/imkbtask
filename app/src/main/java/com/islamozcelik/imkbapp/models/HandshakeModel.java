package com.islamozcelik.imkbapp.models;

public class HandshakeModel {

    private String aesKey;
    private String aesIV;
    private String authorization;
    private String lifeTime;
    private Status status;

    public HandshakeModel(String aesKey, String aesIV, String authorization, String lifeTime, Status status) {
        this.aesKey = aesKey;
        this.aesIV = aesIV;
        this.authorization = authorization;
        this.lifeTime = lifeTime;
        this.status = status;
    }
    public HandshakeModel(){

    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAesIV() {
        return aesIV;
    }

    public void setAesIV(String aesIV) {
        this.aesIV = aesIV;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
