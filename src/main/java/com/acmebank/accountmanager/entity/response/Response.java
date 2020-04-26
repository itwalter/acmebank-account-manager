package com.acmebank.accountmanager.entity.response;

public abstract class Response {
    protected int code;
    protected String message;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public abstract int getCode();

    public abstract void setCode(int code);

    public abstract String getMessage();

    public abstract void setMessage(String message);
}
