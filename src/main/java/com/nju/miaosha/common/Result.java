package com.nju.miaosha.common;


import com.nju.miaosha.exception.CodeMsg;
import lombok.Data;

/**
 * @Author: jaggerw
 * @Description: 封装result返回类型
 * @Date: 2020/9/22
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 成功时返回调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    /**
     * 失败时返回调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<>(codeMsg);
    }

    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg){
        if(codeMsg != null){
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
