package com.acmebank.accountmanager.exception;

public class BusinessException extends Exception {

    public static final ErrorCode BAD_REQUEST = new ErrorCode(400001, 400, "Bad request");
    public static final ErrorCode INVALID_METHOD_ARGUMENT = new ErrorCode(400002, 400, "Invalid method argument");
    public static final ErrorCode TYPE_MISMATCH = new ErrorCode(400003, 400, "Type mismatch");
    public static final ErrorCode METHOD_ARGUMENT_TYPE_MISMATCH = new ErrorCode(400004, 400, "Method argument type mismatch");
    public static final ErrorCode CONSTRAINT_VIOLATION = new ErrorCode(400005, 400, "Constraint violation");
    public static final ErrorCode NOT_FOUND = new ErrorCode(404001, 400, "Not found");
    public static final ErrorCode ACCOUNT_NOT_EXISTS = new ErrorCode(404002, 400, "Account not exists");
    public static final ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405001, 400, "Method not allowed");
    public static final ErrorCode UNKNOWN_ERROR = new ErrorCode(500001, 500, "Unknown error");
    public static final ErrorCode INTERNAL_ERROR = new ErrorCode(500002, 500, "Internal error");

    private ErrorCode errorCode;

    private String customeMessage = null;

    public BusinessException() {
        super(UNKNOWN_ERROR.getMsg());
        this.errorCode = UNKNOWN_ERROR;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String customeMessage) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.customeMessage = customeMessage;
    }

    public int getCode() {
        return errorCode.getCode();
    }

    public int getHttpCode() {
        return errorCode.getHttpCode();
    }

    @Override
    public String getMessage() {
        if (customeMessage == null) {
            return errorCode.getMsg();
        } else {
            return customeMessage;
        }
    }
}
