package com.alibaba.javabase.work;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:47
 */
public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T, ID> {

    private final TransmittableThreadLocal<RepoContext<T, ID>> context;

    public ThreadLocalAggregateManager(Class<? extends T>  targetClass) {
        this.context = new TransmittableThreadLocal<RepoContext<T, ID>>() {
            @Override
            protected RepoContext<T, ID> initialValue() {
                return new RepoContext<>(targetClass);
            }

            @Override
            protected RepoContext<T, ID> childValue(RepoContext<T, ID> parentValue) {
                return initialValue();
            }
        };
    }

    @Override
    public void attach(T aggregate) {
        context.get().attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        context.get().detach(aggregate);
    }

    @Override
    public void remove() {
        context.remove();
    }

    @Override
    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }


    public EntityDiff detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }




}
