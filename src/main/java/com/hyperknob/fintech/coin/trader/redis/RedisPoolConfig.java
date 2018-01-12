package com.hyperknob.fintech.coin.trader.redis;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author Jin Song
 * @desc redis(Jedis) Pool基本配置
 *
 */
@Configuration
//@EnableAutoConfiguration
@ConfigurationProperties(prefix = "redis.pool")
@Data
public class RedisPoolConfig {

    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int numTestsPerEvictionRun;

    @Bean
    public GenericObjectPoolConfig getRedisConfig(){
        GenericObjectPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        config.setTestWhileIdle(true);
        return config;
    }
}
