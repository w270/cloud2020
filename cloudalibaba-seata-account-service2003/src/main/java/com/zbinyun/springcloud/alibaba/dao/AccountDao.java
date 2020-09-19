package com.zbinyun.springcloud.alibaba.dao;

import com.zbinyun.springcloud.alibaba.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    void update(@Param("userId")Integer userId, @Param("money")BigDecimal money);
}
