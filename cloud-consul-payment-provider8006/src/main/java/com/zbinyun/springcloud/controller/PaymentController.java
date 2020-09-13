package com.zbinyun.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consul/payment/provider")
    public String paymentConsul(){
        return "spring cloud whith consul:"+serverPort+"\t"+UUID.randomUUID().toString();
    }
}
