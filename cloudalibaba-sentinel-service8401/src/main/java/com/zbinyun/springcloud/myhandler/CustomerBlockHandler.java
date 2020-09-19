package com.zbinyun.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zbinyun.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息。。。CustomerBlockHandler。");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息。。。CustomerBlockHandler。----2");
    }
}
