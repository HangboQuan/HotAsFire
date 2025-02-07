package com.alibaba.javabase.work;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:22
 */
@Getter
@Setter
public class EntityDiff implements Diff, Iterable<Map.Entry<String, Diff>> {

    static public EntityDiff EMPTY = new EntityDiff();

    private String fieldName;

    private Class<?> fieldType;

    private DiffType type = DiffType.UNCHANGED;

    private Object oldValue;

    private Object newValue;

    private Map<String, Diff> diffMap = new HashMap<>();


    @NotNull
    @Override
    public Iterator<Map.Entry<String, Diff>> iterator() {
        return diffMap.entrySet().iterator();
    }

    public boolean isEmpty() {
        return diffMap.isEmpty();
    }

    public Diff getDiff(String fieldName) {
        return diffMap.get(fieldName);
    }

    public void addDiff(Diff diff) {
        diffMap.put(diff.getFieldName(), diff);
    }

    public Object getNewValueByPropertyDiff(String fieldName) {
        Diff diff = diffMap.get(fieldName);
        if (diff == null || DiffType.MODIFIED != (diff.getType())) {
            return null;
        }
        return diff.getNewValue();
    }
}
