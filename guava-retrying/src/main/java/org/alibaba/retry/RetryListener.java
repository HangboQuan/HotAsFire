package org.alibaba.retry;

/**
 * 监听器为运行代码时发生的多个事件提供回调
 */
public interface RetryListener {

    /**
     * 无论结果如何，都会在rejection predicate和stop策略应用之前触发此方法
     * @param attempt
     * @param <V>
     */
    <V> void onRetry(Attempt<V> attempt);
}
