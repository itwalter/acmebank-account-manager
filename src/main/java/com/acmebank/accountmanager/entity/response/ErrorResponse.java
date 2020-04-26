package com.acmebank.accountmanager.entity.response;

public class ErrorResponse extends Response {
    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        super(code, message);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        super.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        super.message = message;
    }
}
