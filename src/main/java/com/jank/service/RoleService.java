package com.jank.service;

import com.jank.orm.entity.Role;

import java.util.List;

/**
 * 角色
 */
public interface RoleService {
    /**
     * 新增角色
     */
    void add(Role role);
    
    /**
     * 设置权限
     */
    void setPermission(Role role);

    /**
     *
     */
    List<Role> listAll();
    
    /**
     *
     */
    void edit(Role role);
    
    /**
     * @author liulu
     * 2018/1/9 15:48
     */
    void delete(int id);
    
    /**
     *
     */
    List<Role> listByUser(int userId);

    /**
     * 初始化超级管理员的权限，创建superadmin角色，赋予系统所有权限
     */
    Role initSuperAdmin();
}
