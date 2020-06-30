package com.payment.provider.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.payment.provider.dao.PaymentDao;
import com.payment.provider.service.PaymentService;
import com.common.provider.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/6/28 16:32
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment findPaymentById(Long id) {
        return paymentDao.findPaymentById(id);
    }

    /**
     * 熔断开启必须的四个property
     * circuitBreaker.enabled 熔断开关，必须开启
     * circuitBreaker.requestVolumeThreshold 请求的阈值
     * circuitBreaker.sleepWindowInMilliseconds 窗口期
     * circuitBreaker.errorThresholdPercentage 请求失败的比率
     * 熔断执行的顺序为
     * 1判断请求的次数是否达到阈值
     * 2判断请求失败的比率
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(Integer id) {
        return null;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数u，请稍后尝试！";
    }
}
