package com.payment.provider.service;


import com.common.provider.entity.Payment;

public interface PaymentService {
    public int create(Payment payment);
    public Payment findPaymentById(Long id);
}
