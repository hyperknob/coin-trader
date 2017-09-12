package com.hyperseed.fintech.coin.trader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;

/**
 * Created by JinSong on 2017/2/20.
 * Spring boot main class
 * Note: Since Spring boot runs out of Multiple data sources configuration magically,
 *       Should auto config all Beans except DataSourceAutoConfiguration &
 *       DataSourceTransactionManagerAutoConfiguration.
 */
@Configuration
@ImportResource(locations={"classpath*:conf/application*.xml"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@SpringBootApplication
//@EnableWebMvc
public class SpringBootMain extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
//        SpringBoot方式启动
        SpringApplication.run(SpringBootMain.class, args);

//        // 强制加载固定的Container
//        if (null == args) {
//            args = new String[0];
//        }
//        Set<String> bootArgsSet = new HashSet<String>(Arrays.asList(args));
//        bootArgsSet.add("custom");
//        bootArgsSet.add("spring");
//
//        args = new String[bootArgsSet.size()];
//        bootArgsSet.toArray(args);
//
//        // 设定Spring Container加载配置文件的路径
//        System.setProperty(SpringContainer.SPRING_CONFIG, "classpath*:conf/application*.xml");
//
//        com.alibaba.dubbo.container.Main.main(args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(9090);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootMain.class);
    }

//    注册自定义filter
//    @Bean
//    public FilterRegistrationBean createFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new RestEasyStaticContentFilter());
//        registration.addUrlPatterns("/*");
////        registration.addInitParameter("ignore", "(/favicon.ico|/(assets|images|fonts|css|js|res)/.*)");
//        registration.setName("swaggerFilter");
//        registration.setOrder(2);
//        return registration;
//    }
}
