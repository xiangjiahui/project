package com.xjh.gamestory.config;


import com.xjh.gamestory.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册处理器拦截器
 * @author 123
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /** 拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();

        // 白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register");
        patterns.add("/web/login");
        patterns.add("/user/register");
        patterns.add("/user/login");
        patterns.add("/district/**");
        patterns.add("/address/**");
        patterns.add("/product/**");


        // 通过注册工具添加拦截器
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }


    /**
     * 解决头像上传无法立即访问,需要重启才能访问的问题
     * @param registry 类
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:C:\\Project\\game-story\\src\\main\\resources\\static\\upload\\");
    }
}
