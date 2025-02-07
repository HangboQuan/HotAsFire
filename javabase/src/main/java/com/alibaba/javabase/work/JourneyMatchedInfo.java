package com.alibaba.javabase.work;

import lombok.Data;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:29
 */
@Data
public class JourneyMatchedInfo {

    private PaxOrderId paxOrderId;

    private String hitchPercent;

    /**
     * 接单类型
     */
    private EnumMatchType matchType;
}
