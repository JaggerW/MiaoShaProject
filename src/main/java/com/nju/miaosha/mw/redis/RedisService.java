package com.nju.miaosha.mw.redis;

import com.nju.miaosha.utils.JSONUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: jaggerw
 * @Description: redis服务类
 * @Date: 2020/9/21
 */
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取单个对象
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return JSONUtil.string2Bean(str,clazz);
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * 设置对象
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = JSONUtil.bean2String(value);
            if(str == null || str.length() < 1) return false;
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.getExpireSeconds();
            if(seconds <= 0){
                jedis.set(realKey,str);
            }else {
                jedis.setex(realKey,seconds,str);
            }
            return true;
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * 判断是否存在该KEY
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * 原子操作，自增1
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            return2Pool(jedis);
        }
    }

    /**
     * 原子操作，自减1
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally {
            return2Pool(jedis);
        }
    }

    private void return2Pool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
