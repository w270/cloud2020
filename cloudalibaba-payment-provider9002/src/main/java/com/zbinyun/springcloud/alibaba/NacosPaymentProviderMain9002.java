package com.zbinyun.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentProviderMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentProviderMain9002.class);
    }
}
