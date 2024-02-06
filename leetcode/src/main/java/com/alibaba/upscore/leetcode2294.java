package com.alibaba.upscore;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2024/2/6 15:58
 */
public class leetcode2294 {

    // 竞赛分数 1416
    public static void main(String[] args) {

    }

    // 时间复杂度O(n)
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i ++ ) {
            if (nums[i] - num > k) {
                res ++;
                num = nums[i];
            }
        }
        return res;
    }
}
