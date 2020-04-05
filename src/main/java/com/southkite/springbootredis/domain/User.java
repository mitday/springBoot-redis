package com.southkite.springbootredis.domain;

import java.io.Serializable;

/**
 * @author liao xin <liaoxin@mailink.com>
 * @date 2020/4/4 15:56
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4415438719697624729L;


    private String id;

    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
