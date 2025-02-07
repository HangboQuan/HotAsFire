package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:41
 */
public interface AggregateManager<T extends Aggregate<ID>, ID extends Identifier> {

    void attach(T aggregate);

    void detach(T aggregate);

    void remove();

    EntityDiff detectChanges(T aggregate);

    void merge(T aggregate);


    static <T extends Aggregate<ID>, ID extends Identifier> AggregateManager<T, ID> newInstance(Class<T> aggregateClass) {
        return new ThreadLocalAggregateManager<>(aggregateClass);
    }
}
