package com.jank.common;

/**
 * Created by Chenyafeng on 2018/4/4.
 */
public class Constants {
    public static final int PAGE_SIZE = 20;

    public static final class Key {
        //Session中存储图片验证码的key
        public static final String SESSION_KEY_CAPTCHA = "captcha";

        //用户 Session key
        public static final String SESSION_KEY_USER = "session_user";
    }


    public static final class Role {
        public static final int NORMAL = 1;//普通用户
        public static final int ADMIN = 2;//管理员
        public static final int SPECIAL = 3;
    }
}

