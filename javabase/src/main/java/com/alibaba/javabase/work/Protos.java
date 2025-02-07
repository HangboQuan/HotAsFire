package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 22:57
 */
public class Protos {

    public static final Proto<Object> OK = new Proto<>(0, "ok");


    public static <T> Proto<T> ok(T data) {
        return new Proto<>(0, "ok", data);
    }


    public static <T> Proto<T> build(Result<T> result) {
        return new Proto<>(result.getCode(), result.getMessage(), result.getData());
    }
}
