package com.lchao.config;

import com.lchao.common.Result;


public class ResultException extends RuntimeException {
    private final int code;
    private final String msg;

    public ResultException(String message, int code, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Result result() {
        Result result = new Result();
        result.code = Result.ResultCode.Error.getValue();
        result.msg = "服务器错误,未定义的异常";
        if (code > -1) {
            result.msg = msg;
        }
        return result;
    }
}
