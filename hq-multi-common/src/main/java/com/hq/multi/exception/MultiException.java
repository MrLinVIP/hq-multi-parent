package com.hq.multi.exception;


import com.hq.multi.constant.StatusCode;

/**
 * description: 业务异常处理类
 * @author: hutao
 * @date 2019/7/12 16:34
 */
public class MultiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public MultiException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.msg = statusCode.getMsg();
        this.code = statusCode.getCode();
    }

    public MultiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MultiException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MultiException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MultiException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
