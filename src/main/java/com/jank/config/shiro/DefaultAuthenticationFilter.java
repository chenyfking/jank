package com.jank.config.shiro;

import com.jank.common.Constants;
import com.jank.orm.entity.User;
import com.jank.service.UserService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Shiro默认Filter
 */
public class DefaultAuthenticationFilter extends FormAuthenticationFilter {
	@Autowired
	private UserService userService;

	public DefaultAuthenticationFilter() {
	}

	/**
	 * 登录失效并记住我的情况下，把保存在Cookie中的账户信息存储在Session里
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest,
									  ServletResponse servletResponse, Object mappedValue){
		Subject subject = getSubject(servletRequest, servletResponse);
		boolean isAuthenticated = subject.isAuthenticated();
		boolean isRemembered = subject.isRemembered();
		if (!isAuthenticated && isRemembered) {
			User user = (User) subject.getPrincipal();
			userService.login(new User(user.getUsername(), null));
			user = userService.setPermissionCodes(user);
			subject.getSession().setAttribute(Constants.Key.SESSION_KEY_USER, user);
		}
		return isAuthenticated || isRemembered;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))){
			((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		return super.onAccessDenied(request, response);
	}
}
