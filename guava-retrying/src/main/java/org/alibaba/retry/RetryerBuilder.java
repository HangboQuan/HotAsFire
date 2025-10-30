package org.alibaba.retry;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class RetryerBuilder<V> {
    private Predicate<Attempt<V>> rejectionPredicate = Predicates.alwaysFalse();

    public RetryerBuilder<V> retryIfException() {
        // Predicates.or: 逻辑或操作，任一条件满足即返回true
        rejectionPredicate = Predicates.or(rejectionPredicate, new ExceptionClassPredicate<V>(Exception.class));
        return this;
    }

    public static final class ExceptionClassPredicate<V> implements Predicate<Attempt<V>> {
        private final Class<? extends Throwable> exceptionClass;

        public ExceptionClassPredicate(Class<? extends Throwable> exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        @Override
        public boolean apply(Attempt<V> attempt) {
            if (!attempt.hasException()) {
                return false;
            }
            return exceptionClass.isAssignableFrom(attempt.getExceptionCause().getClass());
        }
    }

    /**
     * Predicate的apply方法：给定一个对象，判断其是否满足条件
     * @param <V>
     */
    public static final class ResultPredicate<V> implements Predicate<Attempt<V>> {
        @Override
        public boolean apply(Attempt<V> attempt) {
            if (!attempt.hasResult()) {
                return false;
            }
            return true;
        }
    }

    public static final class ExceptionPredicate<V> implements Predicate<Attempt<V>> {
        @Override
        public boolean apply(Attempt<V> attempt) {
            if (!attempt.hasException()) {
                return false;
            }
            return true;
        }
    }
}
