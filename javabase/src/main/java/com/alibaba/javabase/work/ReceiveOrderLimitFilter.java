package com.alibaba.javabase.work;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author quanhangbo
 * @date 2025-01-21 21:39
 */
@Component
@Order(70)
public class ReceiveOrderLimitFilter implements AbstractReceiveFilter<DriverOrder, PaxOrder> {
    @Override
    public void doFilter(DriverOrder agg, PaxOrder agg2) {

    }
}
