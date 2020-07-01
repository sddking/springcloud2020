package com.order.consumer.controller;

import com.common.provider.entity.Payment;
<<<<<<< HEAD
import com.order.consumer.loadbalancer.MyLoadBalancer;
import com.order.consumer.services.OrderService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
=======
import com.order.consumer.service.OrderFeignService;
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.common.provider.util.CommonResult;

import javax.annotation.Resource;
<<<<<<< HEAD
import java.net.URI;
import java.util.List;
=======
import java.util.concurrent.TimeUnit;
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc

/**
 * @author 180465
 * @date 2020/6/28 22:28
 */
@RestController
public class OrderController {
<<<<<<< HEAD

    //private  String PAYMENT_SERVICE ="http://payment-provider-service/";

    //@Resource
    //private RestTemplate restTemplate;

    //@Resource
    //private MyLoadBalancer myLoadBalancer;

    //@Resource
    //private DiscoveryClient discoveryClient;

    @Resource
    private OrderService orderService;

    @PostMapping(value = "/order/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment){
//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("payment-provider-service");
//        if(serviceInstanceList == null || serviceInstanceList.size()<=0){
//            return null;
//        }else{
//            ServiceInstance serviceInstance = myLoadBalancer.instances(serviceInstanceList);
//            URI uri = serviceInstance.getUri();
//            return  restTemplate.postForObject(uri+"payment",payment,CommonResult.class);
//        }
        return orderService.create(payment);
=======
    //private  String PAYMENT_SERVICE ="http://payment-provider-service/";

//    @Resource
//    private RestTemplate restTemplate;

    @Resource
    private OrderFeignService orderFeignService;

    @PostMapping(value = "/order/payment")
    public CommonResult<Payment> create(@RequestBody Payment payment){
//        return  restTemplate.postForObject(PAYMENT_SERVICE+"payment",payment,CommonResult.class);
        return  orderFeignService.create(payment);
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc
    }

    @GetMapping(value = "/order/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
<<<<<<< HEAD
//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("payment-provider-service");
//        if(serviceInstanceList == null || serviceInstanceList.size()<=0){
//            return null;
//        }else {
//            ServiceInstance serviceInstance = myLoadBalancer.instances(serviceInstanceList);
//            URI uri = serviceInstance.getUri();
//            return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class);
//        }
        return orderService.getPayment(id);
    }

    @GetMapping(value = "/order/get/timeout")
    public String getTimeOut(){
        //openfeign的默认等待时间为1s
        return orderService.getTimeOut();
=======
        //return  restTemplate.getForObject(PAYMENT_SERVICE+"/payment/get/"+id,CommonResult.class);
        return orderFeignService.getPaymentById(id);
>>>>>>> b1528ad452c7b706f99cb12c2303ebcf9a659cdc
    }

}
