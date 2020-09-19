package com.zbinyun.springcloud.alibaba.dao;

import com.zbinyun.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    void create(Order order);
    void update(@Param("userId")Integer userId,@Param("status")Integer status);
}
