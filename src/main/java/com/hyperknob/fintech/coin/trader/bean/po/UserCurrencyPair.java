package com.hyperknob.fintech.coin.trader.bean.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserCurrencyPair extends BasePO {
    private static final long serialVersionUID = -5400356430471488150L;
    private int id;
    private int uid;
    private String exchange;
    private String currencyPair;
    private Date createTime;
    private Date updateTime;
}
