package com.order.consumer.services;

import com.common.provider.entity.Payment;
import com.common.provider.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "payment-provider-service")
public interface OrderService {

    @PostMapping(value = "/payment")
    public CommonResult<Payment> create(Payment payment);

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id);

    @GetMapping(value = "/payment/get/timeout")
    public String getTimeOut();
}
