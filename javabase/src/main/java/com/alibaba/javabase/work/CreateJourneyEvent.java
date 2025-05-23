package com.alibaba.javabase.work;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-22 19:43
 */
@Component
public class CreateJourneyEvent extends JourneyOrderEventBase {

    public CreateJourneyEvent(Proxy<EnumJourneyEvent, PaxOrder, ConsumerExtInfo> proxy) {
        super(proxy);
    }

    @Override
    protected Result<?> prepare(PaxOrder paxOrder, ConsumerExtInfo consumerExtInfo) {
        return null;
    }

    @Override
    protected Result<?> beforeExecute(PaxOrder paxOrder, ConsumerExtInfo consumerExtInfo) {
        return null;
    }

    @Override
    protected Result<?> doExecute(PaxOrder paxOrder, ConsumerExtInfo consumerExtInfo) {
        return null;
    }

    @Override
    protected Result<?> afterExecute(PaxOrder paxOrder, ConsumerExtInfo consumerExtInfo, Result<?> result, Boolean failed) {
        return null;
    }

    @Override
    public List<EnumJourneyEvent> getStrategyTypes() {
        return Collections.singletonList(EnumJourneyEvent.CREATE_JOURNEY);
    }
}
