package com.order.consumer.controller;

import com.common.provider.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.common.provider.util.CommonResult;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/6/28 22:28
 */
@RestController
public class OrderController {
    private  String PAYMENT_SERVICE ="http://payment-provider-service/";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/order/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return  restTemplate.postForObject(PAYMENT_SERVICE+"payment",payment,CommonResult.class);
    }

    @GetMapping(value = "/order/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return  restTemplate.getForObject(PAYMENT_SERVICE+"/payment/get/"+id,CommonResult.class);
    }

}
