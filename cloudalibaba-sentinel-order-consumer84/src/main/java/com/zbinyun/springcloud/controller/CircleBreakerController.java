package com.zbinyun.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zbinyun.springcloud.entities.CommonResult;
import com.zbinyun.springcloud.entities.Payment;
import com.zbinyun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.constraints.Null;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value="fallback",fallback = "handlerFallback")
//    @SentinelResource(value="fallback",blockHandler = "blockHandler",exceptionsToIgnore = {
            //排除某些异常，不再进行托底
//    })
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        if(id == 5){
            throw new IllegalArgumentException("非法参数。。。");
        }
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSql/"+id,CommonResult.class,id);
        if(result.getData() == null){
            throw  new NullPointerException("null,空指针异常");
        }
        return result;
    }
    //fallback
    public CommonResult<Payment> handlerFallback(@PathVariable Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(4444,"handlerFallback,异常内容："+e.getMessage(),payment);
    }

    //blockHandler
    public CommonResult<Payment> blockHandler(@PathVariable Long id,BlockException e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(4445,"handlerFallback,异常内容："+e.getMessage(),payment);
    }

    //feign
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable Long id) {
        //feign调用，只对paymentService调用起作用，本方法抛出异常不起作用
//        if(id == 5){
//            throw new IllegalArgumentException("feign非法参数。。。");
//        }
        CommonResult<Payment> result = paymentService.paymentSql(id);
//        if(result.getData() == null){
//            throw  new NullPointerException("feign,空指针异常");
//        }
        return result;
    }
}
