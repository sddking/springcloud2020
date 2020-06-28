package com.common.provider.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 180465
 * @date 2020/6/28 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;


    public  CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
