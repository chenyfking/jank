package com.jank.orm.entity;

import java.io.Serializable;

/**
 * 权限
 * Created by Chenyafeng on 2018/6/6.
 */
public class Permission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1490591967062565358L;
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Permission(){}
}
