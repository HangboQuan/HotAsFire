package com.alibaba.leetcode_hot_100;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author quanhangbo
 * @date 2023/10/17 22:25
 */
public class 数组中的第K个最大元素 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            pq.add(num);
        }

        while (k != 0 && !pq.isEmpty()) {
            pq.poll();
            k --;
        }
        return pq.peek();

    }
}
