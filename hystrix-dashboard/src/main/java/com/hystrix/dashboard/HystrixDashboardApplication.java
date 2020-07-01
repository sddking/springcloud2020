package com.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author 180465
 * @date 2020/7/1 14:13
 */
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
public class HystrixDashboardApplication {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashboardApplication.class,args);
    }
}
