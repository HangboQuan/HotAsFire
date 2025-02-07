package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:22
 */
public interface Diff {

    String getFieldName();

    Class<?> getFieldType();

    DiffType getType();

    Object getOldValue();

    Object getNewValue();

    boolean isEmpty();
}
