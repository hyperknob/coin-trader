package com.hyperknob.fintech.coin.trader.config;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JinSong on 2018/1/10.
 * WEB MVC 配置
 */
@Order(1)
@Configuration
public class WebConfAdapter extends WebMvcConfigurerAdapter implements ServletContextInitializer {

//  private UserAuthenticationInterceptor securityInterceptor;
//
//  @Autowired
//  public WebConfAdapter(UserAuthenticationInterceptor securityInterceptor) {
//    super();
//    this.securityInterceptor = securityInterceptor;
//  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //ApiKeyCheck拦截路径
    registry.addInterceptor(new ExchangeApiKeyCheckInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/")
            .excludePathPatterns("/index")
            .excludePathPatterns("/ajax/**");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    super.addViewControllers(registry);
    //主页
    registry.addViewController("/").setViewName("forward:/index");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //配置静态资源路径
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    converters.add(responseBodyConverter());
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    super.configureContentNegotiation(configurer);
    configurer.favorPathExtension(false);
  }

  @Override
  public Validator getValidator() {
    return super.getValidator();
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
    source.registerCorsConfiguration("/**", configuration);
    return new CorsFilter(source);
  }

  @Bean
  public HttpMessageConverter<String> responseBodyConverter() {
    //编码
    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
  }

  /**
   * Created by JinSong on 2017/2/24.
   * 初始化LogBack
   * 以纯Dubbo的方式启动时onStartup并不生效，目前已经改为AOP方式拦截接口日志
   * 适用范围为：Web App 或 Springboot
   */
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    System.out.println("------ Initialize logback Listener -----");
    servletContext.addListener(LogbackConfigListener.class);
    servletContext.setInitParameter("logbackExposeWebAppRoot", "false");
    servletContext.setInitParameter("logbackConfigLocation", "classpath:logback.xml");
    System.out.println("------ Initialize logback Listener completely -----");
  }
}
