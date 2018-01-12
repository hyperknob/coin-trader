package com.hyperknob.fintech.coin.trader.redis;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jin Song
 * @desc redis(Jedis) 基本配置
 *
 */
@Configuration
//@EnableAutoConfiguration
@ConfigurationProperties(prefix = "redis.server")
@Data
public class RedisConfig {

    @Autowired
    private GenericObjectPoolConfig jedisPoolConfig;

    private String ip;
    private int port;

    @Bean
    public ShardedJedisPool getJedisPool(){
        JedisShardInfo info = new JedisShardInfo(ip, port);
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(info);
        ShardedJedisPool pool = new ShardedJedisPool(jedisPoolConfig, shards);
        return pool;
    }
}
