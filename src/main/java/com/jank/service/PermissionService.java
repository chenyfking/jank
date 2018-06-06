package com.jank.service;

import com.jank.orm.entity.Permission;

import java.util.List;

/**
 * 权限
 */
public interface PermissionService {
    /**
     *增加权限
     */
    void add(Permission permission);

    /**
     *
     */
    void addList(List<Permission> permissionList);

    /**
     *
     */
    List<Permission> listAll();

    /**
     * 删除权限
     *  根据id删除
     *  根据code删除
     */
    void delete(Permission permission);
}
