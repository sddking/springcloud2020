package com.order.consumer.stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author 180465
 * @date 2020/7/3 0:11
 */
//当存在多个消费者时，存在重复消费的问题,利用分组group的属性解决
//spring cloud Stream中，处于同一个group的消费者为竞争关系，就能够保证消息只会被其中一个应用消费一次，
//不同组会出现重复消费现象,组的流水号不同
//group还可以实现消息持久的功能，若消费者掉线，下次上线时会自动消费未消费的消息
@Component
@EnableBinding(Sink.class)
public class MessageReceiveListener {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者收到消息***********"+message.getPayload()+"\t   port:" + serverPort);
    }
}
