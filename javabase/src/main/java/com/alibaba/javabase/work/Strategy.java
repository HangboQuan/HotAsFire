package com.alibaba.javabase.work;

import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:18
 */
public interface Strategy<E extends Enum<E>, Aggregate, Extend> {
    /**
     * 获取策略类型
     * @return
     */
    List<E> getStrategyTypes();

    /**
     * 执行策略
     * @param aggregate
     * @param extend
     * @return
     */
    Result<?> execute(Aggregate aggregate, Extend extend);
}
