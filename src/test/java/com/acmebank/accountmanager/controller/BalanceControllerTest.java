package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.AccountManagerApplication;
import com.acmebank.accountmanager.entity.request.TransferRequest;
import com.acmebank.accountmanager.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest(classes = AccountManagerApplication.class)
public class BalanceControllerTest {
    @Autowired
    private BalanceController balanceController;

    @Test
    public void testGetBalance_invalidAccount() {
        try {
            balanceController.getBalance(0L);
            fail("Failed to capture exception for invalid account");
        } catch (ConstraintViolationException e) {
            assertEquals("getBalance.account: Invalid account", e.getMessage());
        }
    }

    @Test
    public void testTransfer_invalidQuantity() {
        try {
            TransferRequest req = new TransferRequest();
            req.setFrom(12345678L);
            req.setTo(88888888L);
            req.setCurrency("HKD");
            req.setQuantity("0");
            balanceController.transfer(req);
            fail("Failed to capture exception for invalid quantity");
        } catch (ConstraintViolationException e) {
            assertEquals("transfer.transfer.quantity: Invalid quantity", e.getMessage());
        }
    }

    @Test
    public void testTransfer_missingCurrency() {
        try {
            TransferRequest req = new TransferRequest();
            req.setFrom(12345678L);
            req.setTo(88888888L);
            req.setCurrency(null);
            req.setQuantity("1");
            balanceController.transfer(req);
            fail("Failed to capture exception for missing currency");
        } catch (ConstraintViolationException e) {
            assertEquals("transfer.transfer.currency: must not be null", e.getMessage());
        }
    }
}
