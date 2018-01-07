package com.hyperknob.fintech.coin.trader.market;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.hyperknob.fintech.coin.trader.bean.CustomizedCurrencyPair.HSR_USDT;
import static org.knowm.xchange.currency.CurrencyPair.BTC_USDT;

@Component
public class MarketMonitor {

    private static OkexMarketDataXChange dataXChange = null;

    static {
        Exchange domesticExchange = ExchangeFactory.INSTANCE
                .createExchange(OkCoinExchange.class.getName());
        dataXChange = new OkexMarketDataXChange(domesticExchange);
    }


    /**
     * Scheduled Task
     * Execute per 3s
     */
    @Scheduled(cron="0/3 * * * * ?")
    public void checkMarketData() {
        try {
            // 1.Get market ticker , trade & depth
            dataXChange.getTicker(HSR_USDT);
            // 2.Save to cache or db
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
