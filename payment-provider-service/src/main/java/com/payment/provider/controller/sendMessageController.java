package com.payment.provider.controller;

import com.payment.provider.stream.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/2 23:55
 */
@RestController
@RequestMapping(value = "/message")
public class sendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;


    @GetMapping(value ="/send")
    public String sendMessage(){
        iMessageProvider.send();
        return  "";
    }
}
