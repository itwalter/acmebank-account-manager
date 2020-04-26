package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.entity.Balance;
import com.acmebank.accountmanager.exception.BusinessException;

import java.math.BigDecimal;

public interface BalanceRepository {

    public Balance getBalance(Long account) throws BusinessException;

    public Balance update(Long account, BigDecimal quantity) throws BusinessException;
}
