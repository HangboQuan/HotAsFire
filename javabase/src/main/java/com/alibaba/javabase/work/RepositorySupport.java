package com.alibaba.javabase.work;


import com.alibaba.javabase.work.util.TtlUtil;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:06
 */
public abstract class RepositorySupport <T extends  Aggregate<ID>, ID extends Identifier> implements  Repository<T, ID>{

    @Getter
    private final Class<T> targetClass;

    // aggregateManager维护snapshot
    @Getter
    private final AggregateManager<T, ID> aggregateManager;


    public RepositorySupport(Class<T> targetClass) {
        this.targetClass = targetClass;
        this.aggregateManager = AggregateManager.newInstance(targetClass);
    }

    protected abstract void onInsert(T aggregate);

    protected abstract T onSelect(ID id);

    protected abstract void onUpdate(T aggregate, EntityDiff diff);

    protected abstract void onDelete(T aggregate);

    protected abstract void onBatchInsert(List<T> aggregates);


    /**
     * 让Aggregate可以被追踪
     * @param aggregate
     */
    @Override
    public void attach(@NotNull T aggregate) {
        this.aggregateManager.attach(aggregate);
    }

    /**
     * 让Aggregate不再被追踪
     * @param aggregate
     */
    @Override
    public void detach(@NotNull T aggregate) {
        this.aggregateManager.detach(aggregate);
    }

    @Override
    public void detachAll() {
        this.aggregateManager.remove();
    }

    @Override
    public T find(@NotNull ID id) {
        T aggregate = this.onSelect(id);
        if (aggregate != null) {
            // 下面的attach是为了让对象被追踪 然后才能实现save时定位变更内容
            // 因此在一些查询接口 我们不需要拷贝一份内存快照
            if (TtlUtil.closeAttach()) {
                return aggregate;
            }
            // 让查询出来的对象能够被追踪
            // 如果自己实现了一个定制查询接口，要记得单独调用attach
            this.attach(aggregate);
        }
        return null;
    }

    @Override
    public void remove(@NotNull T aggregate) {
        this.onDelete(aggregate);

        this.detach(aggregate);
    }

    @Override
    public void save(@NotNull T aggregate) {
        if (TtlUtil.closeAttach()) {
            System.out.println("这个链路未保存attach快照，不能save");
            throw new RuntimeException("无效参数");
        }
        // 如果没有ID 直接插入
        if (aggregate.getId() == null) {
            this.onInsert(aggregate);
            this.attach(aggregate);
            return;
        }

        // 做Diff
        EntityDiff diff = this.aggregateManager.detectChanges(aggregate);
        if (diff.isEmpty()) {
            return;
        }

        // 调用update
        this.onUpdate(aggregate, diff);

        // 最终将DB带来的变化更新回AggregateManager
        aggregateManager.merge(aggregate);
    }

    @Override
    public void batchSave(@NotNull List<T> aggregate) {
        this.onBatchInsert(aggregate);
    }
}
