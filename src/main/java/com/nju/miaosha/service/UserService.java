package com.nju.miaosha.service;

import com.nju.miaosha.dao.UserMapper;
import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.exception.CodeMsg;
import com.nju.miaosha.exception.GlobalException;
import com.nju.miaosha.mw.redis.RedisService;
import com.nju.miaosha.mw.redis.UserRedisKey;
import com.nju.miaosha.request.LoginRequest;
import com.nju.miaosha.utils.GlobalParamsUtil;
import com.nju.miaosha.utils.MD5Util;
import com.nju.miaosha.utils.UUIDUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    public UserDO getById(Long id){
        return userMapper.getById(id);
    }

    public UserDO getByToken(HttpServletResponse response, String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        UserDO userDO = redisService.get(UserRedisKey.getByToken,token,UserDO.class);
        if(userDO != null){
            // 更新时间
            saveToken(response,token,userDO);
        }
        return userDO;
    }

    public boolean login(HttpServletResponse response, LoginRequest loginRequest){
        if(loginRequest == null){
            throw new GlobalException(CodeMsg.PARAM_ERROR);
        }
        String mobile = loginRequest.getMobile();
        String password = loginRequest.getPassword();

        // 获取用户信息
        UserDO userDO = userMapper.getById(Long.parseLong(mobile));
        if(userDO == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        // 验证密码
        String realPass = userDO.getPassword();
        String userSalt = userDO.getUserSalt();
        String calPass = MD5Util.formPass2DBPass(password,userSalt);
        if(!realPass.equals(calPass)){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        // 生成token
        String token = UUIDUtil.uuid();
        saveToken(response,token,userDO);
        return true;
    }

    private void saveToken(HttpServletResponse response, String token, UserDO userDO){
        redisService.set(UserRedisKey.getByToken, token, userDO);
        Cookie cookie = new Cookie(GlobalParamsUtil.USER_TOKEN_COOKIE_NAME, token);
        cookie.setMaxAge(GlobalParamsUtil.USER_TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    public boolean regist(HttpServletResponse response, LoginRequest loginRequest) {
        if(loginRequest == null){
            throw new GlobalException(CodeMsg.PARAM_ERROR);
        }
        String userId = loginRequest.getMobile();
        String formPass = loginRequest.getMobile();
        String name = "JaggerW";
        String salt = UUIDUtil.uuid().substring(0,9);
        String dbPass = MD5Util.formPass2DBPass(formPass,salt);
        UserDO userDO = new UserDO();
        userDO.setUserId(Long.parseLong(userId));
        userDO.setUserName(name);
        userDO.setPassword(dbPass);
        userDO.setUserSalt(salt);
        userDO.setLoginCount(0);
        userDO.setRegisterDate(new Date());
        return userMapper.insert(userDO)>0;

    }
}
