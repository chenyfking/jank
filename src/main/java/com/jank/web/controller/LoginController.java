package com.jank.web.controller;

import com.jank.common.Constants;
import com.jank.common.Result;
import com.jank.orm.entity.User;
import com.jank.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录
 * Created by Chenyafeng on 2018/6/6.
 */
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    /**
     * Ajax登录
     */
    @PostMapping("login")
    @ResponseBody
    public Result login(String username, String password, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        User user = null;
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                userService.login(new User(username, null));

                user = userService.setPermissionCodes((User) subject.getPrincipal());
                subject.getSession().setAttribute(Constants.Key.SESSION_KEY_USER, user);
            }
        } catch (Exception e) {
            return Result.newError();
        }
        return Result.newSuccess().withData(user);
    }

    /**
     * 登出
     */
    @PostMapping("logout")
    @ResponseBody
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.newSuccess();
    }

}
