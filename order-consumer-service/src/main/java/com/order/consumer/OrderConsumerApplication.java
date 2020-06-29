package com.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 180465
 * @date 2020/6/28 21:47
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderConsumerApplication {
    public static void  main(String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }
}
