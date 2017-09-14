package com.hyperknob.fintech.coin.trader.market;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketMonitor {

    @Scheduled(cron="0/5 * * * * ?")
    public void checkMarketData() {
        System.out.println("test");
    }
}
