package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:07
 */
public enum EnumMatchType {

    AUTO_BY_DRIVER_ORDER(1, "临时自动接单"),
    AUTO_BY_COMMON_ROUTE(2, "常规路线自动接单"),
    PAX_SELECT_DRIVER_ORDER(15, "乘选司接单"),
    ;

    private Integer code;

    private String type;

    EnumMatchType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
