package com.jank.web.controller;

import com.jank.common.Result;
import com.jank.orm.entity.User;
import com.jank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * Created by Chenyafeng on 2018/6/5.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Result userList(User user, @RequestParam(value="page",defaultValue = "0")int page) {
        return userService.findUserList(user,page);
    }
}
