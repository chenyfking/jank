package com.jank.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 */
@Configuration
@ComponentScan("com.jank")
@ServletComponentScan
public class WebConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/login",
//                        "/logout"
//                );
    }
}
