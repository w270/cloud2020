package com.zbinyun.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zbinyun.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_global_fallbackMethod")
public class HystrixOrderController {

    @Resource
    private HystrixPaymentService paymentService;

    @GetMapping("/hystrix/consumer/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentService.paymentInfo_OK(1);
    }

    @GetMapping("/hystrix/consumer/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUT_fallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000"),
//
//    },threadPoolProperties = {
//            @HystrixProperty(name="coreSize",value="1"),
//    })
    @HystrixCommand
    public String paymentInfo_TIMEOUT(@PathVariable("id") Integer id) throws InterruptedException {
        Thread.sleep(2000);
//        return paymentService.paymentInfo_TIMEOUT(3);
        return "1";
    }
    public String paymentInfo_TIMEOUT_fallbackMethod(@PathVariable("id")Integer id){
        return "order,timeout";
    }

    public String paymentInfo_global_fallbackMethod(){
        return "global异常处理信息，请重试。";
    }


    @GetMapping("/circuit/payment/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
