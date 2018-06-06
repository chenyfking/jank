package com.jank.service.impl;

import com.jank.common.Constants;
import com.jank.common.LogManager;
import com.jank.common.Result;
import com.jank.orm.entity.Permission;
import com.jank.orm.entity.Role;
import com.jank.orm.entity.User;
import com.jank.orm.mapper.UserMapper;
import com.jank.service.RoleService;
import com.jank.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Chenyafeng on 2018/6/5.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public Result findUserList(User user,int page) {
        try {
            Integer start = (page > 0 ? (page-1) * Constants.PAGE_SIZE : null);
            Integer end = (page > 0 ? Constants.PAGE_SIZE : null);
            List<User> list = userMapper.selectUserList(user,start,end);
            return Result.newSuccess().withData(list);
        } catch (Exception e) {
            logger.info("findUserList 出错 : "+user);
            e.printStackTrace();
        }
        return Result.newError();
    }

    @Override
    public User getByUsername(String username) {
        try {
            return userMapper.selectByUsername(username);
        } catch (Exception e) {
            logger.error(username + e.getLocalizedMessage(), e);
        }
        return null;
    }

    @Override
    public void login(User user) {
        if (StringUtils.isBlank(user.getUsername()) && StringUtils.isBlank(user.getPassword())) {
            return;
        }
        try {
            User loginUser = new User();
            loginUser.setUsername(loginUser.getUsername());
            loginUser.setPassword(user.getPassword());
            loginUser.setUpdateTime(new Date());
            userMapper.update(loginUser);
        } catch (Exception e) {
            logger.error(user + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public User setPermissionCodes(User user) {
        List<Role> roles = roleService.listByUser(user.getId());
        if (!roles.isEmpty()) {
            Set<String> permissionCodes = new HashSet<>();
            for (Role role : roles) {
                for (Permission permission : role.getPermissions()) {
                    permissionCodes.add(permission.getCode());
                }
            }
            user.setPermissionCodes(permissionCodes);
        }
        return user;
    }
}
