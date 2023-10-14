package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2023/10/14 21:05
 */
public class 盛最多水的容器 {

    public int maxArea(int[] h) {
        int max = 0;
        int left = 0;
        int right = h.length  - 1;
        while (left < right) {
            max = Math.max((Math.min(h[left], h[right]) * (right - left)), max);
            if (h[left] <= h[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return max;
    }
}
