package com.order.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 180465
 * @date 2020/6/30 16:27
 */
@Configuration
public class FeignConfig {
    /**
     * 日志等级分为
     * NONE 默认的不显示任何的日志
     * BASIC 仅记录请求方法、URL、响应的状态码和执行时间
     * HEADERS 除了BASIC外，还有请求头和响应的头信息
     * FULL 除了HEADERS定义的信息之外，还有请求和响应的正文和元数据
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
