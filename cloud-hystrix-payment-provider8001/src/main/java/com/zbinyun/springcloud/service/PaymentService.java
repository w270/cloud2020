package com.zbinyun.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentinfo_OK,id:"+id+"\t end";
    }

    /**
     * timeout
     * 服务端超时，降级操作,超过2秒后降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUT_handler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })
    public String paymentInfo_TIMEOUT(Integer id){
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentinfo_TIMEOUT,id:"+id+"\t end 耗时 "+timeNumber+"秒钟";
    }

    public String paymentInfo_TIMEOUT_handler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TIMEOUT_handler,id:"+id+"\t end 超时";
    }
    ///////================服务熔断
//    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
//            @HystrixProperty(name="circuitBreaker.enabled",value="true"),//开启断路器
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"), //请求次数
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60"),//失败率达到多少后打开断路器
//    })
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback")
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("****************id 不能为负");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "****************id 不能为负,请稍后重试。id:"+id;
    }
}
