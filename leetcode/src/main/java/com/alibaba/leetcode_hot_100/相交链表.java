package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2023/10/17 21:38
 */


class ListNode {
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}

public class 相交链表 {

    /**
     思路：链表只可能相交于末尾，类似于题目给出的，所以先统计下两个链表的长度，让距离相交点距离相等的距离开始跑
     如果链表A长度大于链表B，那么A从A.len - B.len开始遍历，B从起点开始遍历，则一定能相交于某点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 首先统计下节点个数
        int n1 = 0, n2 = 0, n = 0;
        for (ListNode p = headA; p != null; p = p.next) {
            n1 ++;
        }
        for (ListNode p = headB; p != null; p = p.next) {
            n2 ++;
        }
        n = n1 - n2;
        if (n1 < n2) {
            ListNode temp = headA;
            headA = headB;
            headB = temp;
            n = -n;
        }
        // A的长度一定比B长，先让A跑n1 - n2
        ListNode p1 = headA;
        ListNode p2 = headB;
        for (int i = 0; p1 != null && i < n; p1 = p1.next, i ++ );
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }
}
