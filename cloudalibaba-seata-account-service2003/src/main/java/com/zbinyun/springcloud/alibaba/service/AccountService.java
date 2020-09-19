package com.zbinyun.springcloud.alibaba.service;

import com.zbinyun.springcloud.alibaba.dao.AccountDao;
import com.zbinyun.springcloud.alibaba.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountService {
    @Resource
    private AccountDao accountDao;

    public void decrease(Integer userId, BigDecimal money){
        accountDao.update(userId,money);
    }

}
