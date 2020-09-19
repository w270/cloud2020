package com.zbinyun.springcloud.controller;

import com.zbinyun.springcloud.entities.CommonResult;
import com.zbinyun.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long,Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1l,new Payment(1l,"111111111111111111"));
        hashMap.put(2l,new Payment(2l,"222222222222222222"));
        hashMap.put(3l,new Payment(3l,"33333333333333333333"));
        hashMap.put(4l,new Payment(4l,"44444444444444444"));

    }

    @GetMapping("/paymentSql/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql ,serverPort:"+serverPort,payment);
        return result;
    }
}
