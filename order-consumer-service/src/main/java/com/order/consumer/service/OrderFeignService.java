package com.order.consumer.service;

import com.common.provider.entity.Payment;
import com.common.provider.util.CommonResult;
import com.order.consumer.fallback.OrderFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payment-provider-service",fallback = OrderFeignFallback.class)
public interface OrderFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id")Long id);

    @PostMapping(value = "/payment")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping(value = "/payment/get/timeout")
    String getTimeOut();
}
