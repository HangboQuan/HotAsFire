package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-21 17:08
 */

/**
 * 接单责任链
 * ReceiveTrafficDistributionFilter(90) 车主是否可接当前订单
 *
 * ReceiveOrderLimitFilter(70) 车主接单限制
 *
 * HackDriverReceiveLimitFilter(32) 外挂车主接单限制
 *
 * DriverRegisterFilter(20) 校验车主认证
 *
 * OrderFilter(0) 订单信息校验
 * @param <T>
 * @param <R>
 */
public interface AbstractReceiveFilter<T, R> extends BaseFilter<T, R> {
}
