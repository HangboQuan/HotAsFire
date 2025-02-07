package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-21 17:07
 */
public interface BaseFilter<T, R> {

    void doFilter(T agg, R agg2);
}
