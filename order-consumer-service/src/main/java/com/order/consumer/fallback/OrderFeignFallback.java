package com.order.consumer.fallback;

import com.common.provider.entity.Payment;
import com.common.provider.util.CommonResult;
import com.order.consumer.service.OrderFeignService;
import org.springframework.stereotype.Component;

/**
 * @author 180465
 * @date 2020/6/30 21:38
 */
@Component
public class OrderFeignFallback implements OrderFeignService {
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(500,"getPaymentById Fallback");
    }

    public CommonResult create(Payment payment) {
        return new CommonResult(500,"create Fallback");
    }
}
