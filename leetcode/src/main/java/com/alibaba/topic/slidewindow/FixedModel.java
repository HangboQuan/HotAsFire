package com.alibaba.topic.slidewindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author quanhangbo
 * @date 23-9-6 下午6:59
 */
public class FixedModel {

	public static int[] getSubarrayBeauty(int[] nums, int k, int x) {
		int[] res = new int[nums.length - k + 1];
		int[] bucket = new int[101];
		for (int i = 0; i < nums.length; i ++ ) {
			bucket[nums[i] + 50] ++;
			if (i - k + 1 < 0) {
				continue;
			}
			if (i - k >= 0) {
				bucket[nums[i - k] + 50] --;
			}
			int count = x;
			for (int j = 0; j < bucket.length; j ++ ) {
				count -= bucket[j];
				if (count <= 0) {
					res[i - k + 1] = (j - 50) < 0 ? j - 50 : 0;
					break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(getSubarrayBeauty(new int[]{-3, 1, 2, -3, 0, -3}, 2, 1)));
		System.out.println(Arrays.toString(getSubarrayBeauty(new int[]{1, -1, -3, -2, 3}, 3, 2)));
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] res = new int[nums.length - k + 1];
//		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o2 - o1;
//			}
//		});
//
//		for (int i = 0; i < nums.length; i ++ ) {
//			if (i - k + 1 < 0) {
//				continue;
//			}
//			// 晋升元素
//			pq.offer(nums[i]);
//			if (i - k >= 0) {
//				// 退出元素
//				pq.remove(nums[i - k]);
//			}
//			System.out.println(pq.size());
//			res[i - k + 1] = pq.peek();
//		}

		LinkedList<Integer> queue = new LinkedList<>();
		int max = 0;
		for (int i = 0; i < nums.length; i ++ ) {
			if (nums[i] > max) {
				max = nums[i];
			}
			queue.offer(nums[i]);
			if (i - k + 1 < 0) {
				continue;
			}
			if (i - k >= 0) {
				max = 0;
				queue.remove(i - k);
			}
			res[i - k + 1] = max;

		}
		return res;
	}
}
