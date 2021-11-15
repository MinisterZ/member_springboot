package com.zxy.req;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
import java.util.Date;

import lombok.Data;

import java.io.Serializable;

/**
 * 接收会员查询条件
 */
@Data
public class MemberREQ implements Serializable {

    /**
     * 会员卡号
     */
    private String cardNum;

    /**
     * 会员名字
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 支付类型（'1'现金, '2'微信, '3'支付宝, '4'银行卡）
     */
    private String payType;
}
