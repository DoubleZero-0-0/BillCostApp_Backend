package com.example.backend.config;

import com.example.backend.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/api/login",
                        "/api/register",
                        "/api/emailVerified",
                        "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/v2/api-docs/**"
                )
                .order(Ordered.HIGHEST_PRECEDENCE);
    }
}
