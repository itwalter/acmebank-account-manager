package com.acmebank.accountmanager.entity.response;

import com.acmebank.accountmanager.entity.Balances;

public class BalancesResponse extends Response {
    private Balances balances;

    public BalancesResponse() {
        super(1, "Successful");
    }

    public BalancesResponse(Balances balances) {
        super(1, "Successful");
        this.balances = balances;
    }

    public Balances getBalances() {
        return balances;
    }

    public void setBalances(Balances balances) {
        this.balances = balances;
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
