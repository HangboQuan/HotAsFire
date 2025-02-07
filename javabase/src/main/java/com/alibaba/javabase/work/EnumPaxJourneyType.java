package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:17
 */
public enum EnumPaxJourneyType {

    HITCH_PASSENGER(1, "顺风车", "hitch.passenger"),
        ;
    private Integer code;
    private String msg;
    private String bizCode;

    private EnumPaxJourneyType(Integer code, String msg, String bizCode) {
        this.code = code;
        this.msg = msg;
        this.bizCode = bizCode;
    }
}
