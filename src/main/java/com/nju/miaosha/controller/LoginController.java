package com.nju.miaosha.controller;

import com.nju.miaosha.common.Result;
import com.nju.miaosha.exception.CodeMsg;
import com.nju.miaosha.exception.GlobalException;
import com.nju.miaosha.request.LoginRequest;
import com.nju.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jaggerw
 * @Description: 登录
 * @Date: 2020/9/23
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index.view")
    public String loginView(){
        return "login";
    }

    @PostMapping(value = "/login.action")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Validated LoginRequest loginRequest) throws Exception{
        try {
            userService.login(response,loginRequest);
        }catch (Exception ex){
            throw new GlobalException(CodeMsg.OTHER_ERROR.fillArgs(ex.getMessage()));
        }
        return Result.success(true);
    }

    @PostMapping(value = "/regist.action")
    @ResponseBody
    public Result<Boolean> doRegist(HttpServletResponse response, @Validated LoginRequest loginRequest){
        return Result.success(userService.regist(response,loginRequest));
    }

}
