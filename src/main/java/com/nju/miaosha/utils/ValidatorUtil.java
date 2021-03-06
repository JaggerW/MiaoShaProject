package com.nju.miaosha.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jaggerw
 * @Description: 校验工具类
 * @Date: 2020/9/22
 */
public class ValidatorUtil {
    private static final Pattern mobilePattern = Pattern.compile("1[35678]\\d{9}");

    /**
     * 判断是否符合手机号格式
     * @param inputStr
     * @return
     */
    public static boolean isMobile(String inputStr){
        if(StringUtils.isEmpty(inputStr)){
            return false;
        }
        Matcher m = mobilePattern.matcher(inputStr);
        return m.matches();
    }
}
