package com.order.consumer.controller;

import com.common.provider.entity.Payment;
import com.order.consumer.loadbalancer.MyLoadBalancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.order.consumer.service.OrderFeignService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.common.provider.util.CommonResult;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 180465
 * @date 2020/6/28 22:28
 */
@RestController
@RefreshScope
public class OrderController {

    @Value("${server.name}")
    private String name;

    //private  String PAYMENT_SERVICE ="http://payment-provider-service/";

    //@Resource
    //private RestTemplate restTemplate;

    //@Resource
    //private MyLoadBalancer myLoadBalancer;

    //@Resource
    //private DiscoveryClient discoveryClient;

    //private  String PAYMENT_SERVICE ="http://payment-provider-service/";

    //@Resource
    //private RestTemplate restTemplate;

    @Resource
    private OrderFeignService orderFeignService;

    @PostMapping(value = "/order/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment){
//        return  restTemplate.postForObject(PAYMENT_SERVICE+"payment",payment,CommonResult.class);
        return  orderFeignService.create(payment);
    }

    @GetMapping(value = "/order/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("payment-provider-service");
//        if(serviceInstanceList == null || serviceInstanceList.size()<=0){
//            return null;
//        }else {
//            ServiceInstance serviceInstance = myLoadBalancer.instances(serviceInstanceList);
//            URI uri = serviceInstance.getUri();
//            return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
//        }
        return orderFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/order/get/timeout")
    public String getTimeOut(){
        //openfeign的默认等待时间为1s
        return orderFeignService.getTimeOut();

    }

    @GetMapping(value = "/order/get/config")
    public String getConfigInfo(){
        return name;
    }


}
