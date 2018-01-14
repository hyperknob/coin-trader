package com.hyperknob.fintech.coin.trader.market;

import com.hyperknob.fintech.coin.trader.service.OkexService;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.hyperknob.fintech.coin.trader.constant.CustomizedCurrencyPair.HSR_USDT;

@Component
public class MarketMonitor {

    @Autowired
    private OkexService okexService;


    /**
     * Scheduled Task
     * Execute per 3s
     */
    @Scheduled(cron="0/3 * * * * ?")
    public void checkMarketData() {
        try {
            // 1.Get market ticker , trade & depth
            okexService.getTicker(HSR_USDT);
            // 2.Save to cache or db
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
