package com.zbinyun.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

/**
 * hystrix 异常处理
 */
@Component
public class PaymentFallbackService implements HystrixPaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "hystrix ----paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TIMEOUT(Integer id) {
        return "hystrix ----paymentInfo_TIMEOUT";
    }

    @Override
    public String paymentCircuitBreaker(Integer id) {
        return "error";
    }
}
