package com.acmebank.accountmanager.entity.request;

import com.acmebank.accountmanager.validation.annotation.ValidAccount;
import com.acmebank.accountmanager.validation.annotation.ValidQuantity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferRequest {

    @ValidAccount
    private Long from;

    @ValidAccount
    private Long to;

    @ValidQuantity
    private BigDecimal quantity;

    @NotNull
    private String currency;

    public TransferRequest() {
    }

    public TransferRequest(Long from, Long to, String quantity, String currency) {
        this.from = from;
        this.to = to;
        this.quantity = new BigDecimal(quantity);
        this.currency = currency;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = new BigDecimal(quantity);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
