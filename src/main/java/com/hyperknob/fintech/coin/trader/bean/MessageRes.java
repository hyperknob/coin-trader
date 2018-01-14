package com.hyperknob.fintech.coin.trader.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRes {
    private int code;
    private String message;

    public MessageRes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
