package com.cjp.musiclibrary.config;

import com.cjp.musiclibrary.interceptor.UserInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CJP
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private UserInterceptor userInterceptor;
    private final static String AB_IMG_PATH = "D:\\Download\\ab";
    private final static String ATS_IMG_PATH = "D:\\Download\\ats";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(userInterceptor)
                .excludePathPatterns("/yueyumusic/user/**", "/yueyumusic/imgs/**", "/webjars/**", "/doc.html/**", "/v3/api-docs/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/yueyumusic/imgs/ab/**", "/yueyumusic/imgs/ats/**")
                .addResourceLocations("file:" + AB_IMG_PATH, "file:" + ATS_IMG_PATH);
    }
}
