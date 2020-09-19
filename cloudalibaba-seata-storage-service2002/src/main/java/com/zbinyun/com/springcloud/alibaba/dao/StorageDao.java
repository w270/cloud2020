package com.zbinyun.com.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface StorageDao {
    public void decrease(@Param("productId")Integer productId, @Param("count")Integer count);
}
