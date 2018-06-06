package com.jank.service;

import com.jank.common.Result;
import com.jank.orm.entity.User;

/**
 * Created by Chenyafeng on 2018/6/5.
 */
public interface UserService {
    /**
     * 用户列表
     * @author chenyafeng
     * @date 2018/6/5
     */
    Result findUserList(User user, int page);

    /**
     * 根据用户名获得用户
     * @author chenyafeng
     * @date 2018/6/6
     */
    User getByUsername(String username);

    /**
     * 登录
     * @author chenyafeng
     * @date 2018/6/6
     */
    void login(User user);

    /**
     * 设置账户的权限编码列表
     */
    User setPermissionCodes(User user);

}
