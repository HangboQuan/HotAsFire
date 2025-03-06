package com.alibaba.leetcode_hot_100;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2025-02-27 15:43
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
