package com.nju.miaosha.mw.redis;

/**
 * @Author: jaggerw
 * @Description: redisKey前缀接口类
 * @Date: 2020/9/21
 */
public interface KeyPrefix {

    Integer getExpireSeconds();
    String getPrefix();
}
