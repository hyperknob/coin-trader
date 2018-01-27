package com.hyperknob.fintech.coin.trader.service.impl;

import com.hyperknob.fintech.coin.trader.bean.po.UserCurrencyPair;
import com.hyperknob.fintech.coin.trader.bean.vo.ApiKey;
import com.hyperknob.fintech.coin.trader.cache.ApiKeyPairCache;
import com.hyperknob.fintech.coin.trader.constant.Constants;
import com.hyperknob.fintech.coin.trader.dao.CurrencyPairMapper;
import com.hyperknob.fintech.coin.trader.service.OkexService;
import com.hyperknob.fintech.coin.trader.service.UserMarketService;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

/**
 * OkexService Impl
 */
@Service
public class UserMarketServiceImpl implements UserMarketService {

	private final Logger log = LoggerFactory.getLogger(UserMarketServiceImpl.class);

	@Autowired
	private CurrencyPairMapper mapper;

	@Override
	public List<UserCurrencyPair> getUserCurrencyPairbyUid(Integer uid) {
		return mapper.getByUid(uid);
	}
}
