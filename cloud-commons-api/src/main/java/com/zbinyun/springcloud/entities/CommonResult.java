package com.zbinyun.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //set get
@AllArgsConstructor  //全参
@NoArgsConstructor //空参
public class CommonResult<T> {
    //404 not fond
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code ,String message){
        this(code,message,null);
    }
}
