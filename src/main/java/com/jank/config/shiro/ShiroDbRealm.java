package com.jank.config.shiro;

import com.jank.orm.entity.User;
import com.jank.service.UserService;
import com.jank.util.SessionHolder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro Realm
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		User user = userService.getByUsername((String) token.getPrincipal());
		if (user == null) {
			return null;
		}
		return new SimpleAuthenticationInfo(
				user,
				user.getPassword(),
				ByteSource.Util.bytes(user.getUsername() + "salt"),//TODO 密码盐
				getName()
		);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = SessionHolder.currentUser();
		if (user == null) {
			return null;
		}

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(user.getPermissionCodes());
		return authorizationInfo;
	}
}
