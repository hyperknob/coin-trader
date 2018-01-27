package com.hyperknob.fintech.coin.trader.service.impl;

import com.hyperknob.fintech.coin.trader.bean.vo.ApiKey;
import com.hyperknob.fintech.coin.trader.cache.ApiKeyPairCache;
import com.hyperknob.fintech.coin.trader.constant.Constants;
import com.hyperknob.fintech.coin.trader.service.OkexService;
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
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

/**
 * OkexService Impl
 */
@Service
public class OkexServiceImpl implements OkexService, InitializingBean {

	private final Logger log = LoggerFactory.getLogger(OkexServiceImpl.class);

	/** Market Service */
	private TradeService tradeService;

	/** Trade Service */
	private MarketDataService mdService;

	/** Inner exchange */
	private Exchange exchange;

	/** Inner exchange specification */
	private ExchangeSpecification exchangeSpecification;

	@Override
	public Ticker getTicker(CurrencyPair currencyPair) throws IOException {
		Ticker ticker = mdService.getTicker(currencyPair);
		log.info("{} ticker: {}", currencyPair, reflectionToString(ticker, MULTI_LINE_STYLE));
		return ticker;
	}

	@Override
	public Trades getTrades(CurrencyPair currencyPair) throws IOException {
		Trades trades = mdService.getTrades(currencyPair);
		log.info("{} trades: {}", currencyPair, reflectionToString(trades, MULTI_LINE_STYLE));
		return trades;
	}

	@Override
	public OrderBook getOrderBook(CurrencyPair currencyPair) throws IOException {
		OrderBook orderBook = mdService.getOrderBook(currencyPair);
		log.info("{} order book: {}", currencyPair, reflectionToString(orderBook, MULTI_LINE_STYLE));
		return orderBook;
	}

	@Override
	public OpenOrders getOpenOrders() throws IOException {
		OpenOrders openOrders = tradeService.getOpenOrders();
		log.info("openOrders: {}", reflectionToString(openOrders, MULTI_LINE_STYLE));
		return openOrders;
	}

	@Override
	public void activateApiKey() {
		ApiKey key = ApiKeyPairCache.getKeyPair(Constants.OKEX_API_KEYPAIR_NAME);
		if(null != key) {
			exchangeSpecification.setApiKey(key.getKey());
			exchangeSpecification.setSecretKey(key.getSecret());
			// re-Activate specification
			exchange.applySpecification(exchangeSpecification);
			mdService = exchange.getMarketDataService();
			tradeService = exchange.getTradeService();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 0. Create Exchange instance
		exchange = ExchangeFactory.INSTANCE
				.createExchange(OkCoinExchange.class.getName());
		// 1. Modify & apply the spec
		exchangeSpecification = new ExchangeSpecification(exchange.getClass());
		exchangeSpecification.setSslUri("https://www.okex.com/api");
		exchangeSpecification.setHost("www.okex.com");
//		exchangeSpecification.setExchangeName("OKEX");
		exchangeSpecification.setExchangeDescription("OKEX is a globally oriented crypto-currency trading platform.");
		// set to true to automatically use the Intl_ parameters for ssluri and host
		exchangeSpecification.setExchangeSpecificParametersItem("Use_Intl", true);
		exchangeSpecification.setExchangeSpecificParametersItem("Use_Futures", false);
		exchange.applySpecification(exchangeSpecification);
		// 2. Retrieve service
		mdService = exchange.getMarketDataService();
		tradeService = exchange.getTradeService();
	}
}
