package com.alibaba.weekly.dw112;

import java.util.ArrayList;

/**
 * @author quanhangbo
 * @date 23-9-16 下午4:06
 */
public class leetcode2807 {

	public ListNode insertGreatestCommonDivisors(ListNode head) {
		ArrayList<Integer> ans = new ArrayList<>();
		for (ListNode p = head; p != null; p = p.next) {
			ans.add(p.val);
		}
		int[] num = new int[ans.size() - 1];
		int count = 0;
		for (int i = 0; i < ans.size() - 1; i ++) {
			num[count ++] = findGcd(ans.get(i), ans.get(i + 1));
		}

		int[] target = new int[ans.size() + num.length];
		int l = 0, r = 0;
		int cnt = 0;
		while (l < ans.size() || r < num.length) {
			if (l < ans.size()) {
				target[cnt ++] = ans.get(l ++);
			}
			if (r < num.length) {
				target[cnt ++] = num[r ++];
			}
		}

		ListNode h = null, t = null;
		for (int i = 0; i < target.length; i ++ ) {
			ListNode p = new ListNode(target[i]);
			if (h == null) {
				h = t = p;
			} else {
				t = t.next = p;
			}
		}
		return h;
	}

	public static int findGcd(int var1, int var2) {
		if (var1 < var2) {
			int temp = var1;
			var1 = var2;
			var2 = temp;
		}
		for (int i = var2; i >= 1; i -- ) {
			if (var1 % i == 0 && var2 % i == 0) {
				return i;
			}
		}
		return 1;
	}



	static public class ListNode {
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
}
