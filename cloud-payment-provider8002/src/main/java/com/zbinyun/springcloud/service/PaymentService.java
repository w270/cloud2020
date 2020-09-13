package com.zbinyun.springcloud.service;

import com.zbinyun.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
