package com.nju.miaosha.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
public class MD5Util {

    private static final String SALT = "JaggerW@JaEE12138.com";
    private static String getSaltStr(String salt, String inputStr){
        return "" + salt.substring(1,4) + salt.charAt(7) + inputStr + salt.charAt(8) + salt.substring(3,6);
    }

    /**
     * 字符串MD5加密
     * @param src
     * @return
     */
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    /**
     * 用户上传密码取固定值加密
     * @param inputPass
     * @return
     */
    public static String inputPass2FormPass(String inputPass){
        return md5(getSaltStr(SALT,inputPass));
    }

    /**
     * 加盐加密
     * @param formPass
     * @param userSalt
     * @return
     */
    public static String formPass2DBPass(String formPass, String userSalt){
        return md5(getSaltStr(userSalt,formPass));
    }

    /**
     * 用户密码加固定值加盐加密
     * @param inputPass
     * @param userSalt
     * @return
     */
    public static String inputPass2DBPass(String inputPass, String userSalt){
        return formPass2DBPass(inputPass2FormPass(inputPass),userSalt);
    }



}
