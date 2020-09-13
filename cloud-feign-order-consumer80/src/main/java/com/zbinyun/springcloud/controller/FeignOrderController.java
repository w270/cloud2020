package com.zbinyun.springcloud.controller;

import com.zbinyun.springcloud.entities.CommonResult;
import com.zbinyun.springcloud.entities.Payment;
import com.zbinyun.springcloud.service.FeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignOrderController {
    @Resource
    private FeignPaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentService.paymentFeignTimeout();
    }
}
