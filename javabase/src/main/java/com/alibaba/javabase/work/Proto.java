package com.alibaba.javabase.work;

import lombok.Data;

/**
 * @author quanhangbo
 * @date 2025-01-20 22:56
 */
@Data
public class Proto<T> {

    private int code;

    private String msg;

    private T Data;

    public boolean ok() {
        return this.code == 0;
    }

    public Proto(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Proto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.Data = data;
    }
}
