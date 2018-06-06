package com.jank.util;

import com.jank.common.Constants;
import com.jank.orm.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * 用户登录信息
 */
public class SessionHolder {
    /**
     * @return 当前登录账户
     */
    public static User currentUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.Key.SESSION_KEY_USER);
    }

    /**
     * @return 当前登录账户名
     */
    public static String currentUserName() {
        User user = currentUser();
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

//    /**
//     * @return 当前登录账户公司
//     */
//    public static Company currentAccountCompany() {
//        Account account = currentAccount();
//        if (account != null) {
//            return account.getCompany();
//        }
//        return null;
//    }

//    /**
//     * @return 获得登录账户资源权限
//     */
//    public static List<RoleResource> currentAccountResource(){
//        User account = currentAccount();
//        if(account != null){
//            List<Role> roles = account.getRoles();
//            if(roles != null ){
//                List<RoleResource> list = new ArrayList<>();
//                for(Role role : roles){
//                    if(role.getRoleResource() != null){
//                        list.addAll(role.getRoleResource());
//                    }
//                }
//                return list;
//            }
//        }
//        return Collections.emptyList();
//    }
}
