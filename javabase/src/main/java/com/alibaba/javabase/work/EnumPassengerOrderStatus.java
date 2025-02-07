package com.alibaba.javabase.work;

import java.util.stream.Stream;

/**
 * @author quanhangbo
 * @date 2025-01-20 19:31
 */
public enum EnumPassengerOrderStatus {

    CANCEL(-1, "已取消"),
    MATCHING(10, "匹配中"),
    IN_JOURNEY(20, "行程进行中"),
    FINISHED(30, "已完成"),
    ;

    private EnumPassengerOrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public static EnumPassengerOrderStatus parse(Integer code) {
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findFirst()
                .orElse(null);
    }

    public Integer getCode() {
        return code;
    }
}
