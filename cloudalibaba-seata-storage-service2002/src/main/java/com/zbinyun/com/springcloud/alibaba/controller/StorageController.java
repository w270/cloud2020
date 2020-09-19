package com.zbinyun.com.springcloud.alibaba.controller;

import com.zbinyun.com.springcloud.alibaba.service.StorageService;
import com.zbinyun.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;
    @PostMapping(value="/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")Integer productId, @RequestParam("count")Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"库存扣减成功");
    }
}
