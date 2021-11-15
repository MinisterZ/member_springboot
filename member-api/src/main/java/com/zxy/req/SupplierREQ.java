package com.zxy.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */

@Data
public class SupplierREQ implements Serializable {

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String mobile;

}
