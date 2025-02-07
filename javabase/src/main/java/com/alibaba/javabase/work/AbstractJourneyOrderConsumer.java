package com.alibaba.javabase.work;

import javax.validation.constraints.NotNull;
import java.util.Properties;

/**
 * @author quanhangbo
 * @date 2025-01-22 19:35
 */
public abstract class AbstractJourneyOrderConsumer {

//    public void onApplicationEvent(@NotNull SoaClientStartedEvent event) {
//        // do nothing
//        Properties properties = new Properties();
//        Hms.subscribe("ph_jc_journey_order_hitch_core_consumer", messageListenerWrapper.wrap(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//
//
//                Strategy<EnumJourneyEvent, PaxOrder, ConsumerExtInfo> strategy = journeyEventProxy.getStrategy(EnumJourneyEvent.parse(message.getEvent()));
//                if (strategy == null) {
//                    return;
//                }
//                strategy.execute(new PaxOrder(), new ConsumerExtInfo());
//            }
//        }), properties);
//    }
}
