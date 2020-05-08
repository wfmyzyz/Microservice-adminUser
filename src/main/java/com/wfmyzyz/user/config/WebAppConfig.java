package com.wfmyzyz.user.config;

import com.wfmyzyz.user.interceptor.ControllerBaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private ControllerBaseInterceptor controllerBaseInterceptor;

    /**
    * 允许跨域
    * @param registry
    */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowCredentials(true).allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
//    }

    /**
    * 拦截器
    * @param registry
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerBaseInterceptor).addPathPatterns("/base/**");
    }

    /**
    * 文件路劲映射
    * @param registry
    */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/outimg/**").addResourceLocations("file:E:/ideawork/IDEA/book/");
    }*/

}