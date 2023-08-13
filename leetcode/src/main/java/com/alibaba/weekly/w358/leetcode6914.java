package com.alibaba.weekly.w358;

/**
 * @author quanhangbo
 * @date 2023/8/13 15:45
 */
public class leetcode6914 {

    public static void main(String[] args) {

    }

    public ListNode doubleIt(ListNode head) {
        StringBuilder num = new StringBuilder();
        while(head != null) {
            num.append(head.val);
            head = head.next;
        }
        String twoPair = twoPairSum(num.toString());
        ListNode h = null;
        for (int i = twoPair.length() - 1; i >= 0; i -- ) {
            int t = Integer.parseInt(String.valueOf(twoPair.charAt(i)));
            ListNode node = new ListNode(t);
            node.next = h;
            h = node;
        }
        return h;
    }

    public static String twoPairSum(String num1) {

        if (num1.length() == 1) {
            return String.valueOf(Integer.parseInt(num1) * 2);
        }
        StringBuilder value = new StringBuilder();
        int[] num = new int[num1.length() + 1];
        for (int i = num1.length() - 1; i >= 0; i -- ) {
            int target = Integer.parseInt(String.valueOf(num1.charAt(i))) * 2;
            if (target >= 10) {
                num[i] += target / 10;
            }
            num[i + 1] += target % 10;
        }
        if (num[0] == 0) {
            for (int i = 1; i < num.length; i ++ ) {
                value.append(num[i]);
            }
        } else {
            for (int i = 0; i < num.length; i ++ ) {
                value.append(num[i]);
            }
        }

        return value.toString();
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

