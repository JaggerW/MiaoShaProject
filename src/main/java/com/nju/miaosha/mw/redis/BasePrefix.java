package com.nju.miaosha.mw.redis;

/**
 * @Author: jaggerw
 * @Description: redis-key的前缀
 * @Date: 2020/9/21
 */
public abstract class BasePrefix implements KeyPrefix {
    private Integer expireSeconds;
    private String prefix;

    @Override
    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

    public BasePrefix(String prefix){
        this.expireSeconds = 0;  //0为永不过期
        this.prefix = prefix;
    }

    public BasePrefix(Integer expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix= prefix;
    }
}
