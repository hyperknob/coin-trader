package com.hyperknob.fintech.coin.trader.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.OpenOrders;

import java.io.IOException;


/**
 * OkexService Interface
 */
public interface OkexService extends BaseTraderAgentService {

	public Ticker getTicker(CurrencyPair currencyPair) throws IOException;

	public Trades getTrades(CurrencyPair currencyPair) throws IOException;

	public OrderBook getOrderBook(CurrencyPair currencyPair) throws IOException;

	public OpenOrders getOpenOrders() throws IOException;
}
