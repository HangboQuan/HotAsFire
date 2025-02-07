package com.alibaba.javabase.work;

import java.util.stream.Stream;

/**
 * @author quanhangbo
 * @date 2025-01-20 19:50
 */
public enum EnumDriverOrderStatus {
    CANCEL(-1, "已取消"),
    PUBLISHED(10, "待接单"),
    IN_PROCESS(20, "进行中"),
    COMPLETED(30, "已完成"),
    ;
    private EnumDriverOrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public static EnumDriverOrderStatus parse(Integer code) {
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findFirst()
                .orElse(null);
    }
}
