package com.zbinyun.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.zbinyun.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
