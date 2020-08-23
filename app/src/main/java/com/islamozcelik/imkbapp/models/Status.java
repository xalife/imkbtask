package com.islamozcelik.imkbapp.models;

public class Status {
    private Boolean isSuccess;
    private Error error;

    public Status(Boolean isSuccess, Error error) {
        this.isSuccess = isSuccess;
        this.error = error;
    }
    public Status(){

    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
