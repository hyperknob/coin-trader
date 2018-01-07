package com.hyperknob.fintech.coin.trader.market;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.knowm.xchange.currency.CurrencyPair.*;

/**
 * OkexMarketDataXChange
 */
public class OkexMarketDataXChange {

	private final Logger log = LoggerFactory.getLogger(OkexMarketDataXChange.class);

	/** Market Service */
	private final MarketDataService mdService;

	public OkexMarketDataXChange(Exchange exchange) {
		// 1. Modify the spec
		ExchangeSpecification exchangeSpecification = new ExchangeSpecification(exchange.getClass());
		exchangeSpecification.setSslUri("https://www.okex.com/api");
		exchangeSpecification.setHost("www.okex.com");
		exchangeSpecification.setExchangeName("OKEX");
		exchangeSpecification.setExchangeDescription("OKEX is a globally oriented crypto-currency trading platform.");
		// set to true to automatically use the Intl_ parameters for ssluri and host
		exchangeSpecification.setExchangeSpecificParametersItem("Use_Intl", true);
		exchangeSpecification.setExchangeSpecificParametersItem("Use_Futures", false);
		exchange.applySpecification(exchangeSpecification);
		// 2. Retrieve service
		mdService = exchange.getMarketDataService();
	}

	public void getTicker(CurrencyPair currencyPair) throws IOException {
		Ticker ticker = mdService.getTicker(currencyPair);
		log.info("{} ticker: {}", currencyPair, reflectionToString(ticker, MULTI_LINE_STYLE));
	}

	public void getTrades(CurrencyPair currencyPair) throws IOException {
		Trades trades = mdService.getTrades(currencyPair);
		log.info("{} trades: {}", currencyPair, reflectionToString(trades, MULTI_LINE_STYLE));
	}

	public void getOrderBook(CurrencyPair currencyPair) throws IOException {
		OrderBook orderBook = mdService.getOrderBook(currencyPair);
		log.info("{} trades: {}", currencyPair, reflectionToString(orderBook, MULTI_LINE_STYLE));
	}

	public static void main(String[] args) throws IOException {
		// www.okcoin.cn
		Exchange domesticExchange = ExchangeFactory.INSTANCE
				.createExchange(OkCoinExchange.class.getName());
		OkexMarketDataXChange dataXChange = new OkexMarketDataXChange(domesticExchange);

		dataXChange.getTicker(BTC_USDT);

		dataXChange.getTrades(BTC_USDT);

		dataXChange.getOrderBook(BTC_USDT);
	}

}
