package com.payment.provider.dao;
import com.common.provider.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment findPaymentById(@Param("id")Long id);
}
