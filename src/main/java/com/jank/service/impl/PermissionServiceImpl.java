package com.jank.service.impl;

import com.jank.common.LogManager;
import com.jank.orm.entity.Permission;
import com.jank.orm.mapper.PermissionMapper;
import com.jank.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

/**
 * Created by liulu on 2018/1/9.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private static Logger logger = LogManager.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void add(Permission permission) {
        if (StringUtils.isBlank(permission.getCode()) || permission.getCode().length() > 50) {
            throw new IllegalArgumentException("权限编码不能为空并且长度不能超过50个字符");
        }
        if (StringUtils.isBlank(permission.getName()) || permission.getName().length() > 50) {
            throw new IllegalArgumentException("权限名称不能为空并且长度不能超过50个字符");
        }

        try {
            permissionMapper.insert(permission);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("权限编码不能重复");
        } catch (Exception e) {
            logger.error(permission + e.getLocalizedMessage(), e);
            throw new IllegalStateException("添加权限失败");
        }
    }

    @Override
    public void addList(List<Permission> permissionList) {
        for (Permission permission : permissionList) {
            if (StringUtils.isBlank(permission.getCode()) || permission.getCode().length() > 50) {
                throw new IllegalArgumentException("角色编码不能为空并且长度不能超过50个字符");
            }
            if (StringUtils.isBlank(permission.getName()) || permission.getName().length() > 50) {
                throw new IllegalArgumentException("角色名称不能为空并且长度不能超过50个字符");
            }
        }

        try {
            permissionMapper.insertList(permissionList);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("角色编码不能重复");
        } catch (Exception e) {
            logger.error(permissionList + e.getLocalizedMessage(), e);
            throw new IllegalStateException("添加权限失败");
        }
    }

    @Override
    public List<Permission> listAll() {
        try {
            return permissionMapper.selectAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(Permission permission) {
        try {
            permissionMapper.delete(permission);
        } catch (Exception e) {
            logger.error(permission + e.getLocalizedMessage(), e);
            throw new IllegalStateException("删除权限失败");
        }
    }
}
