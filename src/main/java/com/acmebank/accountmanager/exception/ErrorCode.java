package com.acmebank.accountmanager.exception;

public class ErrorCode {

    private int errorCode;

    private int httpCode;

    private String errorMessage;

    public ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return this.errorCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMsg() {
        return this.errorMessage;
    }

}
