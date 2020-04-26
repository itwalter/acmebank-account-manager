package com.acmebank.accountmanager.entity;

import com.acmebank.accountmanager.validation.annotation.ValidAccount;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Balance implements Serializable {

    @ValidAccount
    private Long account;

    @NotNull
    private String currency;

    private BigDecimal balance;
    private ZonedDateTime lastUpdateTime;

    public Balance() {
    }

    public Balance(Long account, String currency, String balance, ZonedDateTime lastUpdateTime) {
        this.account = account;
        this.currency = currency;
        this.balance = new BigDecimal(balance);
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = new BigDecimal(balance);
    }

    public ZonedDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(ZonedDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
