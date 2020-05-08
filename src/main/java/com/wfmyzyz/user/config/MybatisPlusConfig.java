package com.wfmyzyz.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 限制返回一个
     */
    public final static String LIMIT_ONE = "LIMIT 1";

    /**
     * mybatisPLus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
