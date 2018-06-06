package com.jank.orm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Chenyafeng on 2018/6/5.
 */
public class User extends BaseEntity implements Serializable{
    private static final long serialVersionUID = -5427729041675447721L;

    private String username;

    @JsonIgnore
    private String password;

    private int role;

    /**
     * 权限编码集合
     */
    private Set<String> permissionCodes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Set<String> getPermissionCodes() {
        return permissionCodes;
    }

    public void setPermissionCodes(Set<String> permissionCodes) {
        this.permissionCodes = permissionCodes;
    }

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
