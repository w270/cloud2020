package com.zbinyun.springcloud.alibaba.service;

import com.zbinyun.springcloud.alibaba.dao.OrderDao;
import com.zbinyun.springcloud.alibaba.domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @GlobalTransactional(name="create_order",rollbackFor = Exception.class)
    public void create(Order order ){
        log.info(" ----------新建订单");
        orderDao.create(order);
        log.info("------------扣库存");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------------扣钱");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------------修改订单");
        update(order.getUserId(),0);
    }

    public void update(Integer userId, Integer status){
        orderDao.update(userId,status);
    }
}
