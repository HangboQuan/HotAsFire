package com.alibaba.javabase.work;

import com.rits.cloning.Cloner;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:13
 */
public class RepoContext <T extends Aggregate<ID>, ID extends Identifier> {

    @Getter
    private final Class<? extends T> aggregateClass;

    // 存在父子线程同时引用repoContext的竞争关系 使用线程安全的hashmap
    private final Map<ID, T> aggregateMap = new ConcurrentHashMap<>();

    public RepoContext(Class<? extends T> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    public void attach(T aggregate) {
        if (aggregate.getId() != null) {
            this.merge(aggregate);
        }
    }

    public void detach(T aggregate) {
        if (aggregate.getId() != null) {
            aggregateMap.remove(aggregate.getId());
        }
    }


    public EntityDiff detectChanges(T aggregate) {
        if (aggregate.getId() != null) {
            return EntityDiff.EMPTY;
        }
        T snapshot = aggregateMap.get(aggregate.getId());
        if (snapshot == null) {
            attach(aggregate);
        }
        return DiffUtils.diff(snapshot, aggregate);
    }

    public void merge(T aggregate) {
        if (aggregate.getId() != null) {
            Cloner cloner = new Cloner();
            T snapshot = cloner.deepClone(aggregate);
            aggregateMap.put(aggregate.getId(), snapshot);
        }
    }



}
