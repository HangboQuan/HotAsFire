package com.alibaba.javabase.work;

import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author quanhangbo
 * @date 2025-01-21 1:07
 */
@Builder
public class DiffBuilder {

    private final Field field;
    private final Object oldValue;
    private final Object newValue;
    private final List<Diff> list = new ArrayList<>();


    public void addListItem(Field field, Object newValue) {
        ListItemDiff diff = new ListItemDiff(field.getName(), field.getType(), DiffType.ADDED, null, newValue);
        list.add(diff);
    }

    public EntityDiff build() {
        EntityDiff entityDiff = new EntityDiff();
        if (field != null) {
            entityDiff.setFieldName(field.getName());
            entityDiff.setFieldType(field.getType());
        }
        if (!list.isEmpty()) {
            for (Diff diff : list) {
                entityDiff.addDiff(diff);
            }
            entityDiff.setType(DiffType.MODIFIED);
        }
        entityDiff.setOldValue(oldValue);
        entityDiff.setNewValue(newValue);
        return entityDiff;
    }

    public ListDiff buildList() {
        if (list.isEmpty()) {
            return ListDiff.empty(field);
        }
        return ListDiff.fromList(field, oldValue, newValue, list);
    }

    public void modifyListItem(EntityDiff diff) {
        if (!diff.isEmpty()) {
            list.add(diff);
        }
    }

    public void modifyList(ListDiff diff) {
        if (!diff.isEmpty()) {
            list.add(diff);
        }
    }


    public void modifyEntity(Field field, Entity<?> oldValue, Entity<?> newValue) {
        if (oldValue != null && newValue == null) {
            EntityDiff entityDiff = new EntityDiff();
            if (field != null) {
                entityDiff.setFieldName(field.getName());
                entityDiff.setFieldType(field.getType());
            }
            entityDiff.setType(DiffType.REMOVED);
            entityDiff.setOldValue(oldValue);
            entityDiff.setNewValue(null);
            list.add(entityDiff);
            return;
        }
        list.add(DiffUtils.diffEntity(field, oldValue, newValue));
    }


    public void modifyProperty(Field field, Object oldValue, Object newValue) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            if (CollectionUtils.isEmpty((Collection<?>) oldValue) && CollectionUtils.isEmpty((Collection<?>) newValue)) {
                return;
            }
        }

        if (Map.class.isAssignableFrom(field.getType())) {
            if (CollectionUtils.isEmpty((Collection<?>) oldValue) && CollectionUtils.isEmpty((Collection<?>) newValue)) {
                return;
            }
        }

        if (!Objects.equals(oldValue, newValue)) {
            PropertyDiff propertyDiff = new PropertyDiff(field.getName(), field.getType(), DiffType.MODIFIED, oldValue, newValue);
            list.add(propertyDiff);
        }
    }

    public void removeListItem(Field field, Object oldValue) {
        ListItemDiff diff = new ListItemDiff(field.getName(), field.getType(), DiffType.REMOVED, oldValue, null);
        list.add(diff);
    }

}
