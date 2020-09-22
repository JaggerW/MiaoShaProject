package com.nju.miaosha.exception;

import com.nju.miaosha.common.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

/**
 * @Author: jaggerw
 * @Description: 全局异常处理类
 * @Date: 2020/9/22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    public Result<String> exceptionHandle(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsg());
        }else if(e instanceof BindException){
            BindException ex = (BindException)e;
            String message = ex.getMessage();
            return Result.error(CodeMsg.PARAM_ERROR.fillArgs(message));
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

}
