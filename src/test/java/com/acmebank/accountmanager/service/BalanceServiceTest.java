package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.AccountManagerApplication;
import com.acmebank.accountmanager.entity.request.TransferRequest;
import com.acmebank.accountmanager.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest(classes = AccountManagerApplication.class)
public class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;

    @Test
    public void testTransfer_inexistentAccount() {
        try {
            TransferRequest req = new TransferRequest();
            req.setFrom(1L);
            req.setTo(88888888L);
            req.setCurrency("HKD");
            req.setQuantity("1");
            balanceService.transfer(req);
            fail("Failed to capture inexistent account");
        } catch (BusinessException e) {
            assertEquals(BusinessException.ACCOUNT_NOT_EXISTS.getCode(), e.getCode());
        }
    }
}
