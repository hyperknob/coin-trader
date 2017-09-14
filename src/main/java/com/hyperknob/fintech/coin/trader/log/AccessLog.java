package com.hyperknob.fintech.coin.trader.log;

/**
 * 自定义接口日志开关的SPI
 * Created by JinSong on 2017/3/4.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLog {
    /** 是否激活 */
    boolean enable() default false;
}
