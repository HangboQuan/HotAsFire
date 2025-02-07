package com.alibaba.javabase.work;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:07
 */
public interface Repository<T extends Aggregate<ID>, ID extends Identifier> {

    void attach(@NotNull T aggregate);

    void detach(@NotNull T aggregate);

    void detachAll();

    T find(@NotNull ID id);

    void remove(@NotNull T aggregate);

    void save(@NotNull T aggregate);

    void batchSave(@NotNull List<T> aggregate);
}
