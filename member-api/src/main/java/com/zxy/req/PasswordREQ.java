package com.zxy.req;

import lombok.Data;

import javax.naming.directory.SearchResult;
import java.io.Serializable;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
@Data
public class PasswordREQ implements Serializable {
    //
    private Integer userId;
    //
    private String password;
}
