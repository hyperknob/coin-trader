package com.hyperknob.fintech.coin.trader;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by JinSong on 2017/2/20.
 * Spring boot main class
 * Note: Since Spring boot runs out of Multiple data sources configuration magically,
 *       Should auto config all Beans except DataSourceAutoConfiguration &
 *       DataSourceTransactionManagerAutoConfiguration.
 */
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
@MapperScan("com.hyperknob.fintech.coin.trader.dao")
public class SpringBootMain {

    public static void main(String[] args) {
//        SpringBoot方式启动
        SpringApplication.run(SpringBootMain.class, args);
    }
}
