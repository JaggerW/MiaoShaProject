package com.nju.miaosha.mw.redis;

import com.nju.miaosha.utils.GlobalParamsUtil;

/**
 * @Author: jaggerw
 * @Description: 用户模块redis中前缀
 * @Date: 2020/9/22
 */
public class UserRedisKey extends BasePrefix {
    private UserRedisKey(String prefix) {
        super(prefix);
    }

    private UserRedisKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserRedisKey getById = new UserRedisKey("USER_ID");
    public static UserRedisKey getByToken = new UserRedisKey(GlobalParamsUtil.USER_TOKEN_EXPIRE,"USER_TOKEN");
}
