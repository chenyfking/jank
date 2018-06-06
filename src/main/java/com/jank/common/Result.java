package com.jank.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * Created by cyf
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4175588670835267145L;

    /**
     * 请求成功
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 请求失败
     */
    public static final int CODE_ERROR = -1;
    /**
     * 请求频繁
     */
    public static final int CODE_ROBOT = 2;
    /**
     * 用户未登录
     */
    public static final int CODE_UNAUTHORIZED = 401;


    private int code;
    private String msg;
    private T data;

    public Result() {}

    public static Result newError() {
        Result result = new Result();
        result.setCode(CODE_ERROR);
        return result;
    }

    public static Result newSuccess() {
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        return result;
    }

    public Result makeError() {
        this.code = CODE_ERROR;
        return this;
    }

    public Result makeSuccess() {
        this.code = CODE_SUCCESS;
        return this;
    }

    public Result clearMsg() {
        this.msg = null;
        return this;
    }

    public Result clearData() {
        this.data = null;
        return this;
    }

    public Result withCode(int code) {
        this.code = code;
        return this;
    }

    public Result withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result withData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Result{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
