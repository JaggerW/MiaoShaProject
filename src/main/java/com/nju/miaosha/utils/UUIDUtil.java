package com.nju.miaosha.utils;

import java.util.UUID;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
