package com.zbinyun.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value="HYSTRIX-PAYMENT-PROVIDER",fallback = PaymentFallbackService.class)
public interface HystrixPaymentService {
    @GetMapping("/hystrix/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id);

    @GetMapping("/circuit/payment/{id}")

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
