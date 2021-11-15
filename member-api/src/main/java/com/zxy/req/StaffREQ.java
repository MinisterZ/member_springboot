package com.zxy.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
@Data
public class StaffREQ  implements Serializable {

    private String username;

    private String name;
}
