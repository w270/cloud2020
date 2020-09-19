package com.zbinyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelPaymentProviderMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelPaymentProviderMain9004.class);
    }
}
