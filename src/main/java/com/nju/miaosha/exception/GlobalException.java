package com.nju.miaosha.exception;



/**
 * @Author: jaggerw
 * @Description: 全局异常处理类
 * @Date: 2020/9/22
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 2547281129574824953L;

    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        this.codeMsg = codeMsg;
    }
    public CodeMsg getCodeMsg(){
        return this.codeMsg;
    }

}
