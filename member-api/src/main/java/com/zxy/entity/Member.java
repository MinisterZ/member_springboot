package com.zxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */

@Accessors(chain = true)//级联调方法。注解用来配置lombok如何产生和显示getters和setters的方法,如果为true生成的set方法返回this
@Data
@TableName("tb_member")
public class Member  implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
     * 手机号
     */
    private String phone;

    /**
     * 可用积分
     */
    private Integer integral;

    /**
     * 可用金额
     */
    private Double money;

    /**
     * 支付类型（'1'现金, '2'微信, '3'支付宝, '4'银行卡）
     */
    private String payType;

    /**
     * 会员地址
     */
    private String address;


}
