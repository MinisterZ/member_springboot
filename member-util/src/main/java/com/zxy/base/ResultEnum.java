package com.zxy.base;

import lombok.AllArgsConstructor;
import org.omg.PortableInterceptor.SUCCESSFUL;
import lombok.Data;
import lombok.Getter;


/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(2000, "成功"),
    ERROR(999, "失败");

    private Integer code;
    private String desc;

}