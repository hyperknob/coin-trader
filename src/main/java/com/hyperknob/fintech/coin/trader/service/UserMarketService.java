package com.hyperknob.fintech.coin.trader.service;

import com.hyperknob.fintech.coin.trader.bean.po.UserCurrencyPair;
import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;
import java.util.List;


/**
 * UserMarketService Interface
 */
public interface UserMarketService {

	List<UserCurrencyPair> getUserCurrencyPairbyUid(Integer uid);

}
