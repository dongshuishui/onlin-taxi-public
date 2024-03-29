package com.dongshuishui.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: 东水水
 * @Date: 2023/2/13  13:49
 * @Description: com.dongshuishui.apipassenger.interceptor
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
                .excludePathPatterns("/noauthTest", "/verification-code", "/verification-code-check", "/token-refresh")
                .excludePathPatterns("/test-real-time-order/**");
    }
}
