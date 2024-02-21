package com.alibaba.leetcode_top_100;

/**
 * @author quanhangbo
 * @date 2024/2/21 23:40
 */
public class leetcode2两数相加 {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, head = new ListNode(0), tail;
        tail = head;
        int sum = 0, cp = 0;
        while (p1 != null || p2 != null) {
            int x = p1 == null ? 0 : p1.val;
            int y = p2 == null ? 0 : p2.val;
            sum = x + y + cp;
            cp = sum / 10;
            tail = tail.next = new ListNode(sum % 10);
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (cp == 1) {
            tail = tail.next = new ListNode(1);
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {};
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
