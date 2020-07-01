package com.order.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

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
@EnableCircuitBreaker
@EnableHystrixDashboard
public class OrderConsumerApplication {
    public static void  main(String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }

    @Bean
    public  ServletRegistrationBean<HystrixMetricsStreamServlet>  initServletRegistrationBean(){
        ServletRegistrationBean<HystrixMetricsStreamServlet> servletServletRegistrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>();
        servletServletRegistrationBean.setUrlMappings(Collections.singleton("/hystrix.stream"));
        servletServletRegistrationBean.setServlet(new HystrixMetricsStreamServlet());
        return servletServletRegistrationBean;
    }
}
