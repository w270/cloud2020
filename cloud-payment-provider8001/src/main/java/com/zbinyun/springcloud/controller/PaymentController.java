package com.zbinyun.springcloud.controller;

import com.zbinyun.springcloud.entities.CommonResult;
import com.zbinyun.springcloud.entities.Payment;
import com.zbinyun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.ws.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    //@Resource jdk，@Autowired spring
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("*********插入结果:"+result);
        if(result > 0){
            return new CommonResult(200,"success,serverPort:"+serverPort);
        }else {
            return new CommonResult(444,"fail,serverPort:"+serverPort);
        }
    }

    @RequestMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*********查询结果:"+payment);
        return new CommonResult<Payment>(200,"查询成功:"+serverPort,payment);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String element: services){
            log.info("************element :"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     *  测试负载均衡
     * @return
     */
    @GetMapping(value="/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    /**
     * 测试长流程业务
     * @return
     */
    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi,i am paymentzipkin server fall back.";
    }
}
