package com.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
<<<<<<< HEAD
=======
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 180465
 * @date 2020/6/28 21:47
 */
@SpringBootApplication
@EnableEurekaClient
<<<<<<< HEAD
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
=======
@EnableFeignClients
@EnableHystrix
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc
public class OrderConsumerApplication {
    public static void  main(String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }
}
