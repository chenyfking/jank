package com.jank.config;

import com.jank.config.shiro.DefaultAuthenticationFilter;
import com.jank.config.shiro.ShiroDbRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置Shiro相关Bean
 *
 */
@Configuration
public class ShiroConfigs {
    /**
     * @return Shiro核心Filter配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("rememberMeAuthFilter", new DefaultAuthenticationFilter());

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/bundlejs/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/plugin/**", "anon");
        filterChainDefinitionMap.put("/**", "rememberMeAuthFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * @return 记住我Cookie管理
     */
    public CookieRememberMeManager rememberMeManager(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(7 * 24 * 3600);

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    /**
     * @return 登录密码加密匹配
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcherBean() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    @Bean
    public AuthorizingRealm realmBean() {
        ShiroDbRealm realm = new ShiroDbRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcherBean());
        return realm;
    }

    @Bean
    public SecurityManager securityManagerBean() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realmBean());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean("LifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessorBean() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("LifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreatorBean() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisorBean(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
