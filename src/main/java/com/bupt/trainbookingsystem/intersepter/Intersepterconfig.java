package com.bupt.trainbookingsystem.intersepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Intersepterconfig implements WebMvcConfigurer {
/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Intersepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/")
                .excludePathPatterns("/search");
    }*/
}