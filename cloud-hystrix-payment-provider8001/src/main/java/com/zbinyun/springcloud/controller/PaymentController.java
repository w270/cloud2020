package com.zbinyun.springcloud.controller;

import com.zbinyun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("******** result : "+result);
        return  result;
    }

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_TIMEOUT(id);
        log.info("******** result : "+result);
        return  result;
    }

    @GetMapping("/circuit/payment/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("***********result :"+result);
        return result;
    }
}

