package com.order.consumer.loadbalancer.impl;

import com.order.consumer.loadbalancer.MyLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 180465
 * @date 2020/6/30 14:54
 * 手写轮询算法
 */
//@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 获取第几次请求次数
     */
    private final int getNextIncrement(){
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = current > 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("********"+"第"+next+"次请求*********");
        return next;
    }

    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getNextIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
