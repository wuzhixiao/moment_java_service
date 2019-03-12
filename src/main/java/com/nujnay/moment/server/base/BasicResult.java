package com.nujnay.moment.server.base;

import java.io.Serializable;

public class BasicResult<T> implements Serializable {
    private static final long serialVersionUID = 2766711774515609025L;
    /**
     * 返回码
     */
    private Integer code = 1;
    /**
     * 返回结果
     */
    private Boolean success;
    /**
     * 返回的对象
     */
    private T data;
    /**
     * 返回的错误信息
     */
    private String message;


    public BasicResult() {
        this.success = Boolean.TRUE;
    }

    private BasicResult(T data) {
        this();
        this.data = data;
    }

    private BasicResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.success = Boolean.FALSE;
    }

    private BasicResult(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static <T> BasicResult<T> ok() {
        return new BasicResult<>();
    }

    public static <T> BasicResult<T> ok(T data) {
        return new BasicResult<>(data);
    }

    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BasicResult<T> ok(Integer code, String message) {
        return new BasicResult<>(code, message, Boolean.TRUE);
    }

    public static <T> BasicResult<T> error(Integer code, String message) {
        return new BasicResult<>(code, message);
    }

    public static <T> BasicResult<T> error(BasicResult errorResult) {
        return error(errorResult.getCode(), errorResult.getMessage());
    }

    public static <T> BasicResult<T> matchResult(BasicResult result) {
        if (result.getSuccess()) {
            return ok();
        } else {
            return error(result.getCode(), result.getMessage());
        }
    }
}