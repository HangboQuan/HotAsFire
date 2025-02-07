package com.alibaba.javabase.work;

import com.zhipu.oapi.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author quanhangbo
 * @date 2025-01-21 15:23
 */
@Service
public class PaxOrderRepository extends RepositorySupport<PaxOrder, PaxOrderId> {

    private final JourneyPlatformFacade journeyPlatformFacade;

    @Autowired
    public PaxOrderRepository(JourneyPlatformFacade journeyPlatformFacade) {
        super(PaxOrder.class);
        this.journeyPlatformFacade = journeyPlatformFacade;
    }

    @Override
    protected void onInsert(PaxOrder aggregate) {
        String id = journeyPlatformFacade.publishDriverOrder();
        aggregate.setId(new PaxOrderId(id));

    }

    @Override
    protected PaxOrder onSelect(PaxOrderId paxOrderId) {
        if (Objects.isNull(paxOrderId) || StringUtils.isEmpty(paxOrderId.getId())) {
            throw new IllegalArgumentException("paxOrderId is null or empty");
        }

//        PassengerOrderDTO passengerOrderDTO = journeyPlatformFacade.queryPassengerOrder(paxOrderId.getId());
//        PaxOrder paxOrder = paxOrderMapper.convert(passengerOrderDTO);
//        return paxOrder;
        return null;
    }

    @Override
    protected void onUpdate(PaxOrder aggregate, EntityDiff diff) {

//        onOrderStatusDiff(aggregate, diff);

//        onPlanStartTimeDiff(aggregate, diff);

        onJourneyInfoDiff(aggregate, diff);

//        onPriceDiff(aggregate, diff);

    }

    private void onJourneyInfoDiff(PaxOrder aggregate, EntityDiff diff) {
        EntityDiff onJourneyDiff = (EntityDiff) diff.getDiff("journeyInfo");
        if (onJourneyDiff == null) {
            return ;
        }

        if (onJourneyDiff.getType() == DiffType.MODIFIED) {
//            journeyTimeModify();
            journeyStatusModify(aggregate, diff);
        }
    }


    private void journeyStatusModify(PaxOrder aggregate, EntityDiff journeyDiff) {
        Diff journeyStatus = journeyDiff.getDiff("journeyStatus");
        if (null == journeyStatus || DiffType.MODIFIED != journeyStatus.getType()) {
            return;
        }

        EnumJourneyStatus newValue = (EnumJourneyStatus) journeyStatus.getNewValue();

        switch (newValue) {
            case WAIT_DRIVER_ARRIVAL:
                // 确认行程
                break;
            case WAIT_BY_CAR:
                // 车主到达乘客起点
                break;
            case RIDING:
                // 乘客确认上车
                break;
            case ARRIVAL:
                // 确认到达目的地
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDelete(PaxOrder aggregate) {

    }

    @Override
    protected void onBatchInsert(List<PaxOrder> aggregates) {

    }
}
