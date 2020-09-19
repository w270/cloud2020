package com.zbinyun.com.springcloud.alibaba.service;

import com.zbinyun.com.springcloud.alibaba.dao.StorageDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
public class StorageService {
    @Resource
    private StorageDao storageDao;
    public void decrease(@RequestParam("productId")Integer productId, @RequestParam("count")Integer count){
        storageDao.decrease(productId,count);
    }
}
