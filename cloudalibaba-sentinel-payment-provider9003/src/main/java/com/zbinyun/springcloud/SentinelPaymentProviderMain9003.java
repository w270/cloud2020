package com.zbinyun.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.Serializable;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelPaymentProviderMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelPaymentProviderMain9003.class);
    }
}
