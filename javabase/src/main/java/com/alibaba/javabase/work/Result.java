package com.alibaba.javabase.work;

import lombok.Data;
import org.omg.CORBA.Object;

import java.io.Serializable;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:21
 */
@Data
public class Result<T> implements Serializable {

    private final int code;

    private final String message;

    private final T data;

    private final Throwable exception;

    public static final int OK_CODE = 0;
    public static final int ERROR_CODE = 101;
    public static final Result<Object> OK = new Result<>(OK_CODE, null, null, null);
    public static final Result<Object> FAIL = new Result<>(ERROR_CODE, null, null, null);
    public Result(int code, String message, T data, Throwable exception) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.exception = exception;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(0, null, data, null);
    }

    public boolean isOK() {
        return code == OK_CODE;
    }


    public boolean isFail() {
        return !isOK();
    }

    public boolean isSuccess() {
        return isOK();
    }
}
