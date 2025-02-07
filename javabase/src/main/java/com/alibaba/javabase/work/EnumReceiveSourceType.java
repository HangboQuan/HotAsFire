package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-21 17:38
 */
public enum EnumReceiveSourceType {


    TEMP_JOURNEY_LIST(7, "临时行程列表"),
    NEARBY_PASSENGER_LIST(8, "附近乘客列表（市内路线）"),
    CROSS_CITY_PASSENGER_LIST(9, "跨城乘客列表（跨城路线）"),
    INVITE_DRIVER_FROM_IM(10, "IM邀请司机"),
    DEFAULT_ERROR_SOURCE(-1, "未知场景")
    ;


    private int code;

    private String desc;

    EnumReceiveSourceType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
