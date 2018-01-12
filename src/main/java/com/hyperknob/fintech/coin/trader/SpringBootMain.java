package com.hyperknob.fintech.coin.trader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
public class SpringBootMain {

    public static void main(String[] args) {
//        SpringBoot方式启动
        SpringApplication.run(SpringBootMain.class, args);
    }
}
