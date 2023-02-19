package com.dongshuishui.apidriver.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 东水水
 * @Date: 2023/2/19  16:46
 * @Description: com.dongshuishui.apidriver.interceptor
 * @Version: 1.0
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                //拦截的路径
                .addPathPatterns("/**")
                //不需要拦截的路径
                .excludePathPatterns("/noauth");
    }
}
