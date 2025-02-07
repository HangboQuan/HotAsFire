package com.alibaba.javabase.work;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author quanhangbo
 * @date 2025-01-21 17:33
 */
@Component
@Order(90)
public class ReceiveTrafficDistributionFilter implements AbstractReceiveFilter<DriverOrder, PaxOrder> {


    @Override
    public void doFilter(DriverOrder driverOrder, PaxOrder paxOrder) {
        Boolean checkResult = Boolean.TRUE;
        if (EnumReceiveSourceType.INVITE_DRIVER_FROM_IM.equals(paxOrder.getReceiveSourceType())) {
            // 如果是乘客邀请车主 需要乘客确认是否真的邀请了车主
            if (StringUtils.isBlank(driverOrder.fetchDriverOrderId())) {
                checkResult = Boolean.FALSE;
            }
        }

        if (BooleanUtils.isFalse(checkResult)) {
//            throw new HitchCoreException("ERROR");
        }
    }
}
