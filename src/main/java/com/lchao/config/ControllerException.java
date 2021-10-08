package com.lchao.config;

import com.lchao.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@ResponseBody
public class ControllerException {
    // 处理RollBackError
    @ExceptionHandler(value= ResultException.class)
    public Result defaultTransErrorHandler(ResultException e) {
        Result result=new Result();
        result.code= Result.ResultCode.Error.getValue();
        if (e.getCode()>-1){
            result.code= e.getCode();
            result.msg=e.getMsg();
        }
        else {
            result.msg="服务器错误,未定义的异常1";
        }
        return result;
    }
}
