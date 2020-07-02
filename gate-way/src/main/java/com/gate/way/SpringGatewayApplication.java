package com.gate.way;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 180465
 * @date 2020/7/2 8:24
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringGatewayApplication.class,args);
    }
}
