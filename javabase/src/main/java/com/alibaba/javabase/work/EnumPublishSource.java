package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:57
 */
public enum EnumPublishSource {


    HITCH_PAX_PUBLISH_JOURNEY(1, "顺风车乘客发单"),

    HITCH_PAX_RE_PUBLISH_JOURNEY(2, "顺风车乘客重发单"),

    HITCH_DRIVER_PUBLISH_JOURNEY(3, "顺风车司机发单"),

    HITCH_DRIVER_REPUBLISH_JOURNEY(4, "顺风车司机手动重发单"),

    HITCH_DRIVER_AUTO_REPUBLISH_JOURNEY(5, "顺风车司机自动重发单"),
    ;

    int code;

    String msg;

    EnumPublishSource(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
