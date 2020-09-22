package com.nju.miaosha.common;

import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.service.UserService;
import com.nju.miaosha.utils.GlobalParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jaggerw
 * @Description: 参数解决器，自动向controller中填充userDO对象
 * @Date: 2020/9/22
 */
@Service
public class UserDOArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == UserDO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletResponse response = (HttpServletResponse) nativeWebRequest.getNativeResponse();
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        String paramToken = request.getParameter(GlobalParamsUtil.USER_TOKEN_PARAM_NAME);
        String cookieToken = getCookieToken(request);
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        return userService.getByToken(response,token);
    }

    private String getCookieToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(GlobalParamsUtil.USER_TOKEN_COOKIE_NAME.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
