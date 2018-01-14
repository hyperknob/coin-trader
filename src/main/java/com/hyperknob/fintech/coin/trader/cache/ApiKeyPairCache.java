package com.hyperknob.fintech.coin.trader.cache;

import com.hyperknob.fintech.coin.trader.bean.vo.ApiKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory Cache to Save the api keys
 */
public class ApiKeyPairCache {
    /** Map to store api key pair **/
    private final static Map<String, ApiKey> cache = new ConcurrentHashMap<String, ApiKey>();

    public static ApiKey addKeyPair(String keyName, ApiKey keypair) {
        return cache.put(keyName, keypair);
    }

    public static ApiKey getKeyPair(String keyName) {
        return cache.get(keyName);
    }

    public static ApiKey removeKeyPair(String keyName) {
        return cache.remove(keyName);
    }

    public static void cleanAll() {
        cache.clear();
    }
}
