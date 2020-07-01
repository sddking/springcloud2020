package com.payment.provider.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.payment.provider.dao.PaymentDao;
import com.payment.provider.service.PaymentService;
import com.common.provider.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/6/28 16:32
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment findPaymentById(Long id) {
        return paymentDao.findPaymentById(id);
    }

    /**
     * 熔断开启必须的四个property
     * circuitBreaker.enabled 熔断开关，必须开启
     * circuitBreaker.requestVolumeThreshold 请求的阈值
     * circuitBreaker.sleepWindowInMilliseconds 窗口期
     * circuitBreaker.errorThresholdPercentage 请求失败的比率
     * 熔断执行的顺序为
     * 1判断请求的次数是否达到阈值
     * 2判断请求失败的比率
     * 3将circuitbreaker改为open
     * 4窗口期所有请求被熔断
     * 5窗口期外变为half-on，尝试请求是否成功，成功则将将circuitbreaker改为closed，否则继续下个窗口期
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",
            //groupKey = "strGroupCommand"
            //commandKey = "strCommand"
            //threadPoolKey = "strThreadPool"
            commandProperties = {
            //是否启用断路器
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            //在滚动时间窗中，断路器熔断的最小请求数，默认值为20
            //如果滚动时间窗(默认为10s)内仅收到了19个请求，即使这19个请求都失败了，断路器也不会打开
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            //用来设置断路器打开后的休眠时间窗，休眠结束后则断路器处于半开状态，尝试熔断请求的命令，如果依然失败则继续将断路器设置为打开状态，否则关闭断路器
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //在滚动时间窗中，当请求数量超过circuitBreaker.requestVolumeThreshold时，若请求错误的百分比超过60%，将断路器打开，否则关闭
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")

            /**
             * 其他参数
             *  //隔离策略   THREAD表示线程池，SEMAPHORE信号池隔离
             *  @HystrixProperty(name="execution.isolation.strategy" value="THREAD")
             *  //当选择隔离策略为SEMAPHORE，设置信号池的大小，最大并发数
             *  @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests" value="10")
             *  //配置命令执行的超时时间
             *  @HystrixProperty(name="execution.isolation.thread.timeoutinMilliseconds" value="10")
             *  //是否启用超时时间
             *  @HystrixProperty(name="execution.timeout.enabled" value="true")
             *  //执行超时的时候是否中断
             *  @HystrixProperty(name="execution.isolation.thread.interruptOnTimeout" value="true")
             *  //执行被取消的时候是否中断
             *  @HystrixProperty(name="execution.isolation.thread.interruptOnCancel" value="true")
             *  //允许回调方法执行的最大并发数
             *  @HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests" value="10")
             *  //服务降级是否启用
             *  @HystrixProperty(name="fallback.enabled" value="true")
             *  //断路器强制打开
             *  @HystrixProperty(name="circuitBreaker.forceOpen" value="false")
             *  //断路器强制关闭
             *  @HystrixProperty(name="circuitBreaker.forceClosed" value="false")
             *  //滚动时间窗设置，用于断路器判断健康度时需要收集信息的持续时间
             *  @HystrixProperty(name="metrics.rollingStats.timeinMilliseconds" value="10000")
             *  //设置滚动时间窗统计指标信息时划分“桶”的数量，
             *  //断路器在收集指标信息时候会根据时间窗长度拆分为多个“桶”累计各个度量值，每个“桶”记录了一段时间内的采集指标
             *  //若10s内拆分为10个桶，timeinMilliseconds必须能够被numBuckets整除，否则会抛异常
             *  @HystrixProperty(name="metrics.rollingStats.numBuckets" value="10")
             *  //设置地命令执行的延迟是否采用百分位数跟踪和计算，如果设置为false，则所有的概要统计都会返回-1
             *  @HystrixProperty(name="metrics.rollingPercentile.enabled" value="false")
             *  //设置百分位统计的滚动窗口的持续时间
             *  @HystrixProperty(name="metrics.rollingPercentile.timeinMilliseconds" value="60000")
             *  //设置百分位统计滚动窗口中使用“桶”的数量
             *  @HystrixProperty(name="metrics.rollingPercentile.numBuckets" value="60000")
             *  //设置每个“桶”中保留的最大执行次数，若在滚动的时间窗口内发生超过设定值的执行次数，则从最初的位置开始重写
             *  //增加该值的大小会增加内存的消耗量，增加排序百分位数所需的计算时间
             *  @HystrixProperty(name="metrics.rollingPercentile.bucketSize" value="100")
             *  //设置断路器采集健康信息的间隔等待时间（请求成功、错误的百分比）
             *  @HystrixProperty(name="metrics.healthSnapshot.intervalinMilliseconds" value="500")
             *  //是否开启请求的缓存
             *  @HystrixProperty(name="requestCache.enabled" value="true")
             */
    },
    threadPoolProperties = {
            //设置执行命令线程池的核心线程数，该值为命令执行的最大并发数
            @HystrixProperty(name = "coreSize",value = "10"),
            //设置线程池的最大队列大小，设置为-1线程池将使用SynchronousQueue实现的队列，否则将使用LinkedBlockingQueue实现的队列
            @HystrixProperty(name = "maxQueueSize",value = "-1"),
            //设置队列的拒绝阈值，如果设置了该参数，当队列没有达到最大值也拒绝请求，主要是对LinkedBlockingQueue队列的补充
            //LinkedBlockingQueue队列无法动态修改它的对象大小，该属性可以满足动态调整的需求
            @HystrixProperty(name = "queueSizeRejectionThreshold",value = "5")
    }
    )
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0){
            throw new RuntimeException("*********id不能为负数***********");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功！"+"流水号为"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数u，请稍后尝试！";
    }
}
