package com.order.consumer.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 180465
 * @date 2020/6/30 9:48
 */
//@Configuration
public class MyRule {
    /**
     * 默认为RoundRobin
     * 修改负载规则IRule
     */

    /**
     * RoundRobin轮询算法原理
     * rest接口第几次请求数 % 服务器集群总数量 = 实际调用的服务器位置下标，每次服务重启后则rest接口计数从1开始
     * @return
     */

    /**
     * 自旋锁和CAS理论?
     * @return
     */

    @Bean
    public IRule myselfRule(){
        //随机负载
        return  new RandomRule();
    }
}
