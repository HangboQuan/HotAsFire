package com.alibaba.javabase.work;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author quanhangbo
 * @date 2025-01-21 0:58
 */
public class DiffUtils {

    public static <T extends Entity<?>> EntityDiff diff(T oldValue, T newValue) {
        if (null == oldValue) {
            throw new RuntimeException("old agg value is null, as no agg value attached before updated new value");
        }
        return diffEntity(null, oldValue, newValue);
    }

    public static EntityDiff diffEntity(Field field, Entity<?> oldValue, Entity<?> newValue) {
        DiffBuilder diffBuilder = new DiffBuilder(field, oldValue, newValue);
        Class<? extends Entity> entityClass = newValue.getClass();

        for (Field subField : ReflectionUtils.getFields(entityClass)) {
            Class<?> subFieldType = subField.getType();
            Object oldVal = oldValue == null ? null : ReflectionUtils.readField(subField, oldValue);
            Object newVal = ReflectionUtils.readField(subField, newValue);

            if (List.class.isAssignableFrom(subFieldType) &&
                    (ReflectionUtils.isListItemAssignableTo(Entity.class, (List<?>) oldVal)) ||
                    (ReflectionUtils.isListItemAssignableTo(Entity.class, (List<?>) newVal))) {
                ListDiff listDiff = diffEntityList(subField, (List<Entity<?>>) oldVal, (List<Entity<?>>) newVal);
                diffBuilder.modifyList(listDiff);
            } else if ((null != oldVal && Entity.class.isAssignableFrom(oldVal.getClass()))
                || (null != newVal && Entity.class.isAssignableFrom(newVal.getClass()))) {
                diffBuilder.modifyEntity(subField, (Entity<?>) oldVal, (Entity<?>) newVal);
            } else {
                diffBuilder.modifyProperty(subField, oldVal, newVal);
            }
        }
        return diffBuilder.build();
    }

    public static ListDiff diffEntityList(Field field, List<Entity<?>> oldValues, List<Entity<?>> newValues) {
        DiffBuilder diffBuilder = new DiffBuilder(field, oldValues, newValues);
        Map<Identifier, Entity<?>> oldMap = buildMap(oldValues);
        Map<Identifier, Entity<?>> newMap = buildMap(newValues);

        Set<Identifier> allKeys = new HashSet<>(oldMap.keySet());
        allKeys.addAll(newMap.keySet());

        for (Identifier key : allKeys) {
            Entity<?> oldObject = oldMap.get(key);
            Entity<?> newObject = newMap.get(key);

            if (oldObject != null && newObject == null) {
                diffBuilder.removeListItem(field, oldObject);
                continue;
            }
            if (oldObject == null && newObject != null) {
                diffBuilder.addListItem(field, newObject);
                continue;
            }
            if (!Objects.equals(oldObject, newObject)) {
                EntityDiff entityDiff = diffEntity(field, oldObject, newObject);
                diffBuilder.modifyListItem(entityDiff);
            }
        }
        return diffBuilder.buildList();
    }

    public static <T extends Entity<?>> Map<Identifier, T> buildMap(List<T> values) {
        Map<Identifier, T> map = new HashMap<>();
        if (null != values) {
            for (T value : values) {
                map.put(value.getId(), value);
            }
        }
        return map;
    }

}
