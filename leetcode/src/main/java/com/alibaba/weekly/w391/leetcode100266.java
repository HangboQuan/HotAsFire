package com.alibaba.weekly.w391;

/**
 * @author quanhangbo
 * @date 2024-03-31 21:37
 */
public class leetcode100266 {

    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }

        long res = 0;
        for (int num : dp) {
            res += num;
        }

        return res;
    }
}
