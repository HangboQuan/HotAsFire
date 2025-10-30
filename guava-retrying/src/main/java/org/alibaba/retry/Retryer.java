package org.alibaba.retry;


import com.google.common.base.Predicate;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public final class Retryer<V> {

    // todo: 为什么要声明为不可变类
    private final AttemptTimeLimiter<V> attemptTimeLimiter;
    private final Predicate<Attempt<V>> rejectionPredicate;
    private final Collection<RetryListener> listeners;

    public Retryer(@NonNull AttemptTimeLimiter<V> attemptTimeLimiter,
                   @NonNull Predicate<Attempt<V>> rejectionPredicate,
                   @NonNull Collection<RetryListener> listeners) {
        this.attemptTimeLimiter = attemptTimeLimiter;
        this.rejectionPredicate = rejectionPredicate;
        this.listeners = listeners;
    }


    public V call(Callable<V> callable) throws ExecutionException, RetryException {
        long startTime = System.nanoTime();
        // attemptNum 从1开始计数，表示重试次数
        for (int attemptNum = 1; ; attemptNum++) {
            Attempt<V> attempt;
            try {
                // 调用目标方法
                V result = attemptTimeLimiter.call(callable);
                attempt = new ResultAttempt<>(result, attemptNum);
            } catch(Throwable t) {
                attempt = new ExceptionAttempt<>(t, attemptNum);
            }
            // 遍历所有RetryListener，通知每次重试结果（观察式模式）,多用于日志记录或统计
            for (RetryListener listener : listeners) {
                listener.onRetry(attempt);
            }

            if (!rejectionPredicate.apply(attempt)) {
                // 如果不需要重试，返回结果或抛出异常
                return attempt.get();
            }
        }
    }

    static final class ResultAttempt<V> implements Attempt<V> {

        private final V result;
        private final long attemptNumber;

        public ResultAttempt(V result, long attemptNumber) {
            this.result = result;
            this.attemptNumber = attemptNumber;
        }

        @Override
        public V get() throws ExecutionException {
            return result;
        }

        @Override
        public boolean hasResult() {
            return true;
        }

        @Override
        public boolean hasException() {
            return false;
        }

        @Override
        public V getResult() throws IllegalStateException {
            return result;
        }

        @Override
        public long getAttemptNumber() {
            return attemptNumber;
        }
    }

    static final class ExceptionAttempt<V> implements Attempt<V> {
        private final Throwable exception;
        private final long attemptNumber;

        public ExceptionAttempt(Throwable exception, long attemptNumber) {
            this.exception = exception;
            this.attemptNumber = attemptNumber;
        }

        @Override
        public V get() throws ExecutionException {
            throw new ExecutionException(exception);
        }

        @Override
        public boolean hasResult() {
            return false;
        }

        @Override
        public boolean hasException() {
            return true;
        }

        @Override
        public V getResult() throws IllegalStateException {
            throw new IllegalStateException("No result available, the attempt resulted in an exception.", exception);
        }

        @Override
        public long getAttemptNumber() {
            return attemptNumber;
        }
    }
}
