package com.nju.miaosha.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Author: jaggerw
 * @Description: json工具类
 * @Date: 2020/9/21
 */
public class JSONUtil {

    @SuppressWarnings("unchecked")
    public static <T> T string2Bean(String str, Class<T> clazz){
        if(str == null || str.length() < 1|| clazz == null) return null;
        if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else{
            return JSON.parseObject(str,clazz);
        }
    }

    public static <T> String bean2String(T object){
        if(object == null) return null;
        Class<?> clazz = object.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return "" + object;
        }else if(clazz == long.class || clazz == Long.class){
            return "" + object;
        }else if(clazz == String.class){
            return (String) object;
        }else{
            return JSON.toJSONString(object);
        }
    }
}
