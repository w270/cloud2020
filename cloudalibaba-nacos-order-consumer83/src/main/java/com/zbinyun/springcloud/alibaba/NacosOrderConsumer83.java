package com.zbinyun.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.swing.*;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderConsumer83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderConsumer83.class);
    }
}
