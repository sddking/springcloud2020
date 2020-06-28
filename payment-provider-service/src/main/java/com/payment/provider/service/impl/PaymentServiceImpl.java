package com.payment.provider.service.impl;

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
}
