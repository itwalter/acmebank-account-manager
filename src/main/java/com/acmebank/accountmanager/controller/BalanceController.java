package com.acmebank.accountmanager.controller;


import com.acmebank.accountmanager.entity.Balance;
import com.acmebank.accountmanager.entity.Balances;
import com.acmebank.accountmanager.entity.request.TransferRequest;
import com.acmebank.accountmanager.entity.response.BalanceResponse;
import com.acmebank.accountmanager.entity.response.BalancesResponse;
import com.acmebank.accountmanager.entity.response.ErrorResponse;
import com.acmebank.accountmanager.entity.response.Response;
import com.acmebank.accountmanager.exception.BusinessException;
import com.acmebank.accountmanager.service.BalanceService;
import com.acmebank.accountmanager.validation.annotation.ValidAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = "/balances/accounts/{account}", method = RequestMethod.GET)
    public ResponseEntity<Response> getBalance(@PathVariable(value = "account", required = true) @ValidAccount Long account) {
        try {
            Balance balance = balanceService.getBalance(account);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(new BalanceResponse(balance));
        } catch (BusinessException e) {
            return ResponseEntity.status(e.getHttpCode())
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/balances/transfers", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Response> transfer(@RequestBody @Valid TransferRequest transfer) {
        try {
            Balances balances = balanceService.transfer(transfer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(new BalancesResponse(balances));
        } catch (BusinessException e) {
            return ResponseEntity.status(e.getHttpCode())
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(new ErrorResponse(e.getCode(), e.getMessage()));
        }
    }
}
