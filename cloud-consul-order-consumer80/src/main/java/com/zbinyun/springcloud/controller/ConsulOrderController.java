package com.zbinyun.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsulOrderController {
    public static final String PAYMENT_URL = "http://consul-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul/order/consumer")
    public String paymentInfo(){
        return restTemplate.getForObject(PAYMENT_URL+"/consul/payment/provider",
                String.class);
    }

}
