package com.payment.provider.stream.service.impl;

import com.payment.provider.stream.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 180465
 * @date 2020/7/2 23:47
 */
@EnableBinding(Source.class) //定义消息的推送管道,定义生产者
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;   //消息发送的管道

    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("****************serial"+serial);
        return null;
    }
}
