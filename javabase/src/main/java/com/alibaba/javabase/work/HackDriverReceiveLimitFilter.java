package com.alibaba.javabase.work;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author quanhangbo
 * @date 2025-01-21 21:40
 */
@Order(32)
@Component
public class HackDriverReceiveLimitFilter implements AbstractReceiveFilter<DriverOrder, PaxOrder> {
    @Override
    public void doFilter(DriverOrder driverOrder, PaxOrder paxOrder) {
        // 查询司机外挂信息
//        DriverPersonas driverPersonas = driverOrder.getDriverPersonas();
//        if (Objects.isNull(driverPersonas.getUserHackInfo()) || Objects.isNull(driverPersonas.getUserHackInfo().getFeatureValue()))
//            return ;
//        }
//
//        // 命中风控
//        if (driverPersonas.getUserHackInfo().getFeatureValue().equals("risk")) {
//            String toast = "因持续使用外挂，在%s之前无法接单，请立即卸载，持续使用，账号被封禁";
//            throw new RuntimeException(toast);
//        }
//        return ;

    }
}
