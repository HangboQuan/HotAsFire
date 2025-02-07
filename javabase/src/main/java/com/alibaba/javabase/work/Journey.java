package com.alibaba.javabase.work;

import com.alibaba.javabase.work.entity.UserInfo;

import java.util.Objects;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:36
 */
public class Journey implements Entity<JourneyId> {

    private JourneyId id;

    private DriverOrderId driverOrderId;

    private PaxOrderId paxOrderId;

    private UserId driverUserId;

    private EnumJourneyStatus journeyStatus;


    public Boolean gtOrderStatus(int code) {
        return checkOrderStatusNotNull() && journeyStatus.getCode() > code;
    }
    private Boolean checkOrderStatusNotNull() {
        return Objects.nonNull(journeyStatus);
    }

    public Long fetchDriverId() {
        return Objects.nonNull(driverUserId) ? driverUserId.getId() : null;
    }
    @Override
    public JourneyId getId() {
        return null;
    }
}
