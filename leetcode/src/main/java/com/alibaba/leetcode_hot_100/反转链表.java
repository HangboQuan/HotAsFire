package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2023/10/17 21:49
 */
public class 反转链表 {

    // 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode p;
        ListNode newHead = null;
        while (head != null) {
            p = head;
            head = head.next;
            p.next = newHead;
            newHead = p;
        }
        return newHead;
    }

    // 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode p1, p2;
        p1 = p2 = head;
        boolean flag = false;
        while (p2 != null && p2.next != null && !flag) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                flag = true;
            }
        }
        return flag;
    }
}
