package com.alibaba.javabase.work;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author quanhangbo
 * @date 2025-01-21 21:32
 */
@Component
@Order(0)
public class OrderFilter implements AbstractReceiveFilter<DriverOrder, PaxOrder> {
    @Override
    public void doFilter(DriverOrder driverOrder, PaxOrder paxOrder) {
        if (paxOrder == null) {
            throw new RuntimeException("无效乘客行程");
        }

        if (EnumPassengerOrderStatus.CANCEL.equals(paxOrder.getOrderStatus())) {
            throw new RuntimeException("乘客行程已取消");
        }
        // 如果已经被接单
        if (paxOrder.getOrderStatus().getCode() > EnumPassengerOrderStatus.MATCHING.getCode()) {
            throw new RuntimeException("乘客行程已被接单");
        }
    }
}
