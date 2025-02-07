package com.alibaba.javabase.work;

import lombok.Getter;

/**
 * @author quanhangbo
 * @date 2025-01-22 19:13
 */
@Getter
public enum EnumJourneyEvent {
    CREATE_JOURNEY("createJourney"),
    JOURNEY_CONFIRMED("journeyConfirmed"),
    DRIVER_ARRIVER("driverArriver"),
    PAX_ON_CAR("paxOnCar"),
    ARRIVER("arriver"),
    CANCEL("cancel"),
    ;



    private String event;

    EnumJourneyEvent(String event) {
        this.event = event;
    }

    public static EnumJourneyEvent parse(String event) {
        for (EnumJourneyEvent value : values()) {
            if (value.event.equals(event)) {
                return value;
            }
        }
        return null;
    }
}
