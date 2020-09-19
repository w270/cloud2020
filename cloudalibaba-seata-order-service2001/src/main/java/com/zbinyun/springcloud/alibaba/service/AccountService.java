package com.zbinyun.springcloud.alibaba.service;

import com.zbinyun.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value="seata-account-service")
@Component
public interface AccountService {
    @PostMapping(value="/account/decrease")
    CommonResult decrease(@RequestParam("userId")Integer userId, @RequestParam("money")BigDecimal money);
}
