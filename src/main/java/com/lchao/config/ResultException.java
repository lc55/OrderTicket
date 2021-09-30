package com.lchao.config;

import com.lchao.common.Result;


public class ResultException extends RuntimeException{
    private int code=-1;
    private String msg;
    public ResultException(String message, int code,Throwable throwable) {
        super(message,throwable);
        this.code=code;
        this.msg=message;
    }

    public ResultException(String message,int code) {
        super(message);

    }

    public ResultException(String... message) {
        StringBuilder para = new StringBuilder("参数错误：");
        for (String s : message) {
            para.append(s);
        }
        System.out.println(para);

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result result(){
        Result result=new Result();
        result.code= Result.ResultCode.Error.getValue();
        result.msg="服务器错误,未定义的异常";
        if (code>-1){
            result.msg=msg;
        }
       return result;
    }


}
