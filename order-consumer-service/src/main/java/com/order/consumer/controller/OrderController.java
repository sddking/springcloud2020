package com.order.consumer.controller;

import com.common.provider.entity.Payment;
import com.order.consumer.service.OrderFeignService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.common.provider.util.CommonResult;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 180465
 * @date 2020/6/28 22:28
 */
@RestController
public class OrderController {
    //private  String PAYMENT_SERVICE ="http://payment-provider-service/";

//    @Resource
//    private RestTemplate restTemplate;

    @Resource
    private OrderFeignService orderFeignService;

    @PostMapping(value = "/order/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment){
//        return  restTemplate.postForObject(PAYMENT_SERVICE+"payment",payment,CommonResult.class);
        return  orderFeignService.create(payment);
    }

    @GetMapping(value = "/order/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        //return  restTemplate.getForObject(PAYMENT_SERVICE+"/payment/get/"+id,CommonResult.class);
        return orderFeignService.getPaymentById(id);
    }

}
