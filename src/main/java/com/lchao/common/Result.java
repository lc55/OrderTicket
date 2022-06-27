package com.lchao.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lchao.config.ResultException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    public int code;
    public Integer curPage = 1;
    public Integer totalCount = 0;
    public Integer pageSize = 10;
    public String msg;
    public Object data;

    public Result() {
    }

    public Result(int code){
        this.code = code;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data, int curPage, int pageSize, int totalCount) {
        this.code = 1;
        this.msg = "";
        this.data = data;
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public int getCode() {
        return this.code;
    }

    public static Result OK() {
        return new Result(ResultCode.OK.getValue(), "");
    }

    public static Result OK(String msg) {
        return new Result(ResultCode.OK.value, msg);
    }

    public static Result OK(Object data) {
        return new Result(ResultCode.OK.value, "", data);
    }

    public static Result Error(String msg) {
        return new Result(ResultCode.Error.value, msg);
    }

    public static Result RollBackError(String msg) {
        throw new ResultException(msg, 0, new Throwable("ok"));
    }


    public static Result page(IPage page) {
        return new Result(page.getRecords(), Long.valueOf(page.getCurrent()).intValue(), Long.valueOf(page.getSize()).intValue(), Long.valueOf(page.getTotal()).intValue());
    }

    public enum ResultCode {
        Error(0),
        OK(1);

        private final int value;

        ResultCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}

