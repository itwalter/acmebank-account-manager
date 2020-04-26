package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.entity.Balance;
import com.acmebank.accountmanager.entity.Balances;
import com.acmebank.accountmanager.entity.request.TransferRequest;
import com.acmebank.accountmanager.exception.BusinessException;
import com.acmebank.accountmanager.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Balance getBalance(Long account) throws BusinessException {
        return balanceRepository.getBalance(account);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Balances transfer(@Valid TransferRequest transfer) throws BusinessException {
        // FX rate should be implemented for multi-currency;
        Balance fromBalance = balanceRepository.update(transfer.getFrom(), transfer.getQuantity().negate());
        Balance toBalance = balanceRepository.update(transfer.getTo(), transfer.getQuantity());

        Balances result = new Balances();
        result.add(fromBalance);
        result.add(toBalance);
        return result;
    }
}
