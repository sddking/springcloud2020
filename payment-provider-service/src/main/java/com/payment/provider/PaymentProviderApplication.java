package com.payment.provider;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class PaymentProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(PaymentProviderApplication.class,args);
    }

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> initServletRegistrationBean(){
        ServletRegistrationBean<HystrixMetricsStreamServlet> servletServletRegistrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>();
        servletServletRegistrationBean.setUrlMappings(Collections.singleton("/hystrix.stream"));
        servletServletRegistrationBean.setServlet(new HystrixMetricsStreamServlet());
        return servletServletRegistrationBean;
    }
}