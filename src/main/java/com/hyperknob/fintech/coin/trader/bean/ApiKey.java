package com.hyperknob.fintech.coin.trader.bean;

import lombok.Data;

@Data
public class ApiKey {
    private String keyName;
    private String key;
    private String secret;
}
