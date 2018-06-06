package com.jank.service.impl;

import com.jank.common.LogManager;
import com.jank.orm.entity.Permission;
import com.jank.orm.entity.Role;
import com.jank.orm.mapper.PermissionMapper;
import com.jank.orm.mapper.RoleMapper;
import com.jank.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.transaction.TransactionException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void add(Role role) {
        if (StringUtils.isBlank(role.getCode()) || role.getCode().length() > 50) {
            throw new IllegalArgumentException("角色编码不能为空并且长度不能超过50个字符");
        }
        if (StringUtils.isBlank(role.getName()) || role.getName().length() > 50) {
            throw new IllegalArgumentException("角色名称不能为空并且长度不能超过50个字符");
        }
        try {
            roleMapper.insert(role);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException("角色编码不能重复");
        } catch (Exception e) {
            logger.error(role + e.getLocalizedMessage(), e);
            throw new IllegalStateException("添加角色失败");
        }
    }

    @Override
    @Transactional
    public void setPermission(Role role) {
        try {
            roleMapper.updatePermissions(role);
        } catch (Exception e) {
            logger.error(role + e.getLocalizedMessage(), e);
            throw new TransactionException();
        }
    }

    @Override
    public List<Role> listAll() {
        try {
            return roleMapper.selectAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public void edit(Role role) {
        if (StringUtils.isBlank(role.getCode()) || role.getCode().length() > 50) {
            throw new IllegalArgumentException("角色编码不能为空并且长度不能超过50个字符");
        }
        if (StringUtils.isBlank(role.getName()) || role.getName().length() > 50) {
            throw new IllegalArgumentException("角色名称不能为空并且长度不能超过50个字符");
        }

        try {
            roleMapper.update(role);
        } catch (Exception e) {
            logger.error(role + e.getLocalizedMessage(), e);
            throw new IllegalStateException("编辑角色失败");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            roleMapper.delete(id);
        } catch (Exception e) {
            logger.error(id + e.getLocalizedMessage(), e);
            throw new TransactionException();
        }
    }

    @Override
    public List<Role> listByUser(int userId) {
        try {
            return roleMapper.selectByUser(userId);
        } catch (Exception e) {
            logger.error(userId + e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Role initSuperAdmin() {
        Role superAdminRole = roleMapper.selectByCode("superadmin");
        if (superAdminRole == null) {
            superAdminRole = new Role("superadmin", "超级管理员");
            add(superAdminRole);
            superAdminRole.setPermissions(new ArrayList<>());
        }
        List<Permission> permissionList = permissionMapper.selectAll();
        permissionList.removeAll(superAdminRole.getPermissions());
        if (!permissionList.isEmpty()) {
            superAdminRole.setPermissions(permissionList);
            roleMapper.insertPermissions(superAdminRole);
        }
        return superAdminRole;
    }
}
