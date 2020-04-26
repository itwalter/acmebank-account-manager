package com.acmebank.accountmanager.repository.impl;

import com.acmebank.accountmanager.entity.Balance;
import com.acmebank.accountmanager.exception.BusinessException;
import com.acmebank.accountmanager.mapper.H2BalanceMapper;
import com.acmebank.accountmanager.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Primary
public class H2BalanceRepository implements BalanceRepository {
    @Autowired
    private H2BalanceMapper balanceMapper;

    @Override
    public Balance getBalance(Long account) throws BusinessException {
        try {
            return balanceMapper.getBalance(account);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessException.UNKNOWN_ERROR, "Unknown exception - error:[" + e.getMessage() + "]");
        }
    }

    @Override
    public Balance update(Long account, BigDecimal quantity) throws BusinessException {
        try {
            int numAffected = 0;
            while (numAffected < 1) {
                Balance original = getBalance(account);
                if (original == null)
                    throw new BusinessException(BusinessException.ACCOUNT_NOT_EXISTS, "Acount not exists - account:[" + account + "]");
                numAffected = balanceMapper.updateBalance(account, original.getBalance(), original.getBalance().add(quantity), original.getLastUpdateTime());
            }
            return getBalance(account);
        } catch (BusinessException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessException.UNKNOWN_ERROR, "Unknown exception - error:[" + e.getMessage() + "]");
        }
    }


}
