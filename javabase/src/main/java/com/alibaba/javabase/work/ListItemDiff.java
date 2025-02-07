package com.alibaba.javabase.work;

import lombok.Value;

/**
 * @author quanhangbo
 * @date 2025-01-21 1:10
 */
@Value
public class ListItemDiff implements Diff {
    String fieldName;

    Class<?> fieldType;

    DiffType type;

    Object oldValue;

    Object newValue;


    @Override
    public boolean isEmpty() {
        return false;
    }
}
