package com.payment.provider.controller;

import com.payment.provider.service.PaymentService;
import com.common.provider.entity.Payment;
import org.springframework.web.bind.annotation.*;
import com.common.provider.util.CommonResult;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/6/28 16:34
 */
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment")
    public CommonResult create(@RequestBody Payment payment){
        try {
            int result = paymentService.create(payment);
            if (result > 0) {
                return new CommonResult(200, "插入成功!",null);
            } else {
                return new CommonResult(500, "插入失败!",null);
            }
        }
        catch (Exception ex){
            return new CommonResult(500, "插入失败!",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult getPaymentById(@PathVariable("id")Long id){
        try{
            Payment payment = paymentService.findPaymentById(id);
            if(payment!=null){
                return  new CommonResult(200, "查询成功!",payment);
            }else{
                return  new CommonResult(200, "无对应记录!" + id,null);
            }
        }catch (Exception ex){
            return  new CommonResult(500, "查询失败!",null);
        }
    }
}
