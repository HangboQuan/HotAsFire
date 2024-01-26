package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2024/1/26 16:36
 */
public class 最大子数组和 {

    // 3 -1 9 8
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {

            if (sum > 0) {
                // 增益效果 则添加
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
