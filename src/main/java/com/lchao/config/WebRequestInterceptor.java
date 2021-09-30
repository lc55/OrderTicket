package com.lchao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebRequestInterceptor implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> patterns = new ArrayList<>();
//        patterns.add("/user/login");
//        patterns.add("/user/register");
//        patterns.add("/admin/manage/login");
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns("/**");
    }
}
