package com.zbinyun.springcloud.alibaba.service;

import com.zbinyun.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="seata-storage-service")
@Component
public interface StorageService {

    @PostMapping(value="/storage/decrease")
    CommonResult decrease(@RequestParam("productId")Integer productId,@RequestParam("count")Integer count);
}
