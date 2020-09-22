package com.nju.miaosha.config;

import com.nju.miaosha.common.UserDOArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserDOArgumentResolver userDOArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userDOArgumentResolver);
    }
}
