package com.hyperknob.fintech.coin.trader.dao;

import com.hyperknob.fintech.coin.trader.bean.po.UserCurrencyPair;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Currency Pair Mapper
 */
public interface CurrencyPairMapper {
    @Select("SELECT * FROM user_currency_pair")
    @Results({
            @Result(property = "uid",  column = "uid"),
            @Result(property = "exchange", column = "exchange"),
            @Result(property = "currencyPair", column = "currency_pair"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<UserCurrencyPair> getAll();

    @Select("SELECT * FROM user_currency_pair WHERE uid = #{uid}")
    @Results({
            @Result(property = "uid",  column = "uid"),
            @Result(property = "exchange", column = "exchange"),
            @Result(property = "currencyPair", column = "currency_pair"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<UserCurrencyPair> getByUid(Integer uid);

    @Insert("INSERT INTO user_currency_pair(uid,currency_pair) VALUES(#{uid}, #{currencyPair})")
    void insert(UserCurrencyPair currencyPair);

    @Delete("DELETE FROM user_currency_pair WHERE uid =#{uid}")
    void deleteByUid(Integer uid);
}
