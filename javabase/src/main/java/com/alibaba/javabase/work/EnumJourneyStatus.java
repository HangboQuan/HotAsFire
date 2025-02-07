package com.alibaba.javabase.work;

import java.util.stream.Stream;

/**
 * @author quanhangbo
 * @date 2025-01-20 18:29
 */
public enum EnumJourneyStatus {

    CANCELED(-1, "已取消"),
    WAIT_RECEIVING(10, "待车主接单"),
    WAIT_PAY(20, "待支付"),
    WAIT_DRIVER_ARRIVAL(30, "已支付，待车主到达"),
    WAIT_BY_CAR(40, "待上车"),
    RIDING(50, "行程中"),
    ARRIVAL(60, "乘客到达目的地"),
    ;
    private EnumJourneyStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public static EnumJourneyStatus parse(Integer code) {
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findFirst()
                .orElse(null);
    }

    public Integer getCode() {
        return code;
    }
}
