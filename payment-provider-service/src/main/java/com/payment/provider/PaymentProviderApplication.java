package com.payment.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(PaymentProviderApplication.class,args);
    }
}
