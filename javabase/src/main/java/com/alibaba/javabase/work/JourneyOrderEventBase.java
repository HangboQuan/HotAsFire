package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-22 19:42
 */
public abstract class JourneyOrderEventBase extends AbstractStrategy<EnumJourneyEvent, PaxOrder, ConsumerExtInfo> {
    public JourneyOrderEventBase(Proxy<EnumJourneyEvent, PaxOrder, ConsumerExtInfo> proxy) {
        super(proxy);
    }
}
