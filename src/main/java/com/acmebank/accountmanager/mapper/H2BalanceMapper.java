package com.acmebank.accountmanager.mapper;

import com.acmebank.accountmanager.entity.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Mapper
public interface H2BalanceMapper {

    @Select("SELECT account, CAST(balance AS CHAR) AS balance, currency, last_update_time AS lastUpdateTime FROM balances "
            + "WHERE account = #{account} LIMIT 1")
    Balance getBalance(@Param("account") Long account);

    @Update("UPDATE balances SET balance = #{newBalance}, last_update_time = now() " +
            "WHERE account = #{account} and balance = #{oldBalance} and last_update_time = #{lastUpdateTime}")
    int updateBalance(@Param("account") Long account, @Param("oldBalance") BigDecimal oldBalance,
                      @Param("newBalance") BigDecimal newBalance, @Param("lastUpdateTime") ZonedDateTime lastUpdateTime);
}
