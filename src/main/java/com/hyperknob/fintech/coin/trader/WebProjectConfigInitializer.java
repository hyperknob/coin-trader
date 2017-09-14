package com.hyperknob.fintech.coin.trader;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.apache.log4j.PropertyConfigurator;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by JinSong on 2017/2/24.
 * 初始化LogBack
 * 以纯Dubbo的方式启动时onStartup并不生效，目前已经改为AOP方式拦截接口日志
 * 适用范围为：Web App 或 Springboot
 */
//@Deprecated
@Order(1)
@Configuration
public class WebProjectConfigInitializer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Initialize logback Listener.......................................");
        servletContext.addListener(LogbackConfigListener.class);
        servletContext.setInitParameter("logbackExposeWebAppRoot", "false");
        servletContext.setInitParameter("logbackConfigLocation", "classpath:conf/logback.xml");
        System.out.println("Initialize logback Listener completely !...........................");
    }
}
