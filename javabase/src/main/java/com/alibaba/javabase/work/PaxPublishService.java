package com.alibaba.javabase.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author quanhangbo
 * @date 2025-01-20 22:02
 */
@Service
public class PaxPublishService {

    private final PublishJourneyProxy<PaxOrder, PublishExtendInfo> publishJourneyProxy;

    private final PaxOrderAssembler paxOrderAssembler;

    private final PublishExtendAssembler publishExtendAssembler;

    @Autowired
    public PaxPublishService(PublishJourneyProxy<PaxOrder, PublishExtendInfo> publishJourneyProxy,
                             PaxOrderAssembler paxOrderAssembler,
                             PublishExtendAssembler publishExtendAssembler) {
        this.publishJourneyProxy = publishJourneyProxy;
        this.paxOrderAssembler = paxOrderAssembler;
        this.publishExtendAssembler = publishExtendAssembler;
    }

    public Proto<?> paxPublishJourney(@Valid PaxPublishJourneyRequest request, @NotNull EnumPublishSource publishSource) {
        PaxOrder paxOrder = paxOrderAssembler.assembler(request);


        PublishExtendInfo extendInfo = publishExtendAssembler.assemblePublish(request);
        Strategy<EnumPublishSource, PaxOrder, PublishExtendInfo> strategy = publishJourneyProxy.getStrategy(publishSource);

        return Protos.ok(strategy.execute(paxOrder, extendInfo));

    }


}
