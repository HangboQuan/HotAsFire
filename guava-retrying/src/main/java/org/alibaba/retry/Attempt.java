package org.alibaba.retry;

import java.util.concurrent.ExecutionException;

/**
 * 尝试调用，结果要么是调用返回的结果，要么是调用抛出的异常
 * @param <V>
 */
public interface Attempt<V> {

    /**
     * 返回尝试的结果（如果有）
     * @return 尝试的结果
     * @throws ExecutionException 如果抛出异常，抛出的异常将被设置为ExecutionException
     */
    public V get() throws ExecutionException;

    /**
     * 判断调用是否返回结果
     * @return true: 返回结果 false: 抛出异常
     */
    public boolean hasResult();

    /**
     * 判断调用是否抛出异常
     * @return true：抛出异常 false: 返回结果
     */
    public boolean hasException();

    /**
     * 获取调用结果
     * @return 调用结果
     * @throws IllegalStateException 调用未返回结果抛了异常，抛出IllegalStateException
     */
    public V getResult() throws IllegalStateException;

    /**
     * 获取调用抛出的异常
     * @return 调用抛出的异常
     * @throws IllegalStateException 如果调用未抛出异常，抛出IllegalStateException
     */
    public Throwable getExceptionCause() throws IllegalStateException;

    /**
     * 尝试次数，从1开始计数
     * @return
     */
    public long getAttemptNumber();
}
