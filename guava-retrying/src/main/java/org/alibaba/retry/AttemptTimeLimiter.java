package org.alibaba.retry;

import java.util.concurrent.Callable;

/**
 * 任何单次尝试都有一定的次数限制，如果超过限制，可能会被中断
 * @param <V>
 */
public interface AttemptTimeLimiter<V>{

    V call(Callable<V> callable) throws Exception;
}
