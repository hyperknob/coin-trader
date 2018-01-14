package com.hyperknob.fintech.coin.trader.service;

import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;


/**
 * OkexService Interface
 */
public interface OkexService extends BaseService {

	public void getTicker(CurrencyPair currencyPair) throws IOException;

	public void getTrades(CurrencyPair currencyPair) throws IOException;

	public void getOrderBook(CurrencyPair currencyPair) throws IOException;

	public void getOpenOrders() throws IOException;
}
