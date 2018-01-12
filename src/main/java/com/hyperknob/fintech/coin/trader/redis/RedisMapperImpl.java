package com.hyperknob.fintech.coin.trader.redis;

import com.hyperknob.fintech.coin.trader.constant.Constants;
import org.springframework.stereotype.Component;

@Component
public class RedisMapperImpl implements RedisMapper{
    @Override
    public void put(String key, String value) {
        put(key, value, Constants.REDIS_DEFAULT_EXPIRE);
    }

    @Override
    public void put(String key, String value, int expire) {
        RedisUtils.set(key, value, expire);
    }

    @Override
    public String get(String key) {
        return RedisUtils.get(key);
    }

    @Override
    public boolean isExisted(String key) {
        return RedisUtils.exists(key);
    }

    @Override
    public void del(String key) {
        RedisUtils.remove(key);
    }
}
