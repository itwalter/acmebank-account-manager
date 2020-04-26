package com.acmebank.accountmanager.entity.response;

import com.acmebank.accountmanager.entity.Balance;

public class BalanceResponse extends Response {
    private Balance balance;

    public BalanceResponse() {
        super(1, "Successful");
    }

    public BalanceResponse(Balance balance) {
        super(1, "Successful");
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
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
