package com.alibaba.interview.bytedance;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2023/11/16 21:23
 */
class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {

        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;

        }
    }
    HashMap<Integer, DLinkedNode> map = new HashMap<>();
    int size;
    int capacity;
    DLinkedNode head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        node.next.prev = node;
        head.next = node;
    }
    public void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;

    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node == null) {
            if (size == capacity) {
                DLinkedNode tail = removeTail();
                map.remove(tail.key);
                size --;
            }
            DLinkedNode newNode = new DLinkedNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            size ++;
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
