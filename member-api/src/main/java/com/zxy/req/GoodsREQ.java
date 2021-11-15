package com.zxy.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsREQ implements Serializable {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 供应商id
     */
    private Integer supplierId;

}
