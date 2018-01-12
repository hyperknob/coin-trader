package com.hyperknob.fintech.coin.trader.redis;

/**
 * Redis数据Mapper
 */
public interface RedisMapper {

    /**
     * 通用性put，key默认60分钟TTL
     * @param key
     * @param value
     */
    public void put(String key, String value);

    /**
     * 通用性put
     * @param key
     * @param value
     * @param expire
     */
    public void put(String key, String value, int expire);

    /**
     * 通用性get
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 通用性isExisted
     * @param key
     * @return
     */
    public boolean isExisted(String key);

    /**
     * 通用性delete
     * @param key
     */
    public void del(String key);
}