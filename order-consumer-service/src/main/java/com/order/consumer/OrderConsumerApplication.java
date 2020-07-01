package com.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 180465
 * @date 2020/6/28 21:47
 */
@SpringBootApplication
@EnableEurekaClient
/**
 *手写轮询算法
 */
//@EnableDiscoveryClient
/**
 * 使用自定义IRule规则
 */
//@RibbonClient(name="payment-provider-service",configuration = MyRule.class)
/**
 * 使用OpenFeign远程调用
 */
@EnableFeignClients
@EnableHystrix
public class OrderConsumerApplication {
    public static void  main(String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }
}
