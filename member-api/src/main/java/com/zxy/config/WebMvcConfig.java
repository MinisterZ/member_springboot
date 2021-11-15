package com.zxy.config;

import com.zxy.filter.AuthenticationFilter;
import net.sf.jsqlparser.statement.create.table.ExcludeConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @version JDK  1.8.151
 * @Author: Mirrors
 * @Description: Chengdu City
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationFilter)
//                //拦截所有请求
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login");
    }
}
