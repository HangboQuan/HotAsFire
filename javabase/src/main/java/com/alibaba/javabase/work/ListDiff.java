package com.alibaba.javabase.work;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Documented;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-21 1:13
 */
@Getter
public class ListDiff implements Diff, Iterable<Diff> {

    String fieldName;

    Class<?> fieldType;

    DiffType type;

    Object oldValue;

    Object newValue;

    private List<Diff> items = new ArrayList<>();


    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @NotNull
    @Override
    public Iterator<Diff> iterator() {
        return items.iterator();
    }

    private ListDiff() {
    }

    public static ListDiff empty(Field field) {
        ListDiff listDiff = new ListDiff();
        listDiff.fieldName = field.getName();
        listDiff.fieldType = field.getType();
        listDiff.type = DiffType.UNCHANGED;
        return listDiff;
    }

    public static ListDiff from(Field field, Object oldValue, Object newValue) {
        ListDiff listDiff = new ListDiff();
        listDiff.fieldName = field.getName();
        listDiff.fieldType = field.getType();
        listDiff.oldValue = oldValue;
        listDiff.newValue = newValue;
        listDiff.type = DiffType.MODIFIED;
        return listDiff;
    }


    public static ListDiff fromList(Field field, Object oldValue, Object newValue, List<Diff> diffs) {
        ListDiff listDiff = from(field, oldValue, newValue);
        listDiff.items = diffs;
        return listDiff;
    }

}
