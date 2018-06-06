package com.jank.orm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * Created by Chenyafeng on 2018/6/6.
 */
public class Role extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6624985169487605142L;
    private String name;
    private String code;
    /**
     * 权限集合
     */
    private List<Permission> permissions;

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

    public Role() {}

    public Role(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
