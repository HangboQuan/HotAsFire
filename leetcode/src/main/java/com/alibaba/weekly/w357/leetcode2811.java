package com.alibaba.weekly.w357;

import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/8/8 22:12
 */
public class leetcode2811 {
    public static void main(String[] args) {

    }

    /**
     * 学会将题意转化
     * @param nums
     * @param m
     * @return
     */
    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        if (n <= 2) return true;
        for (int i = 1; i < n; i ++) {
            if (nums.get(i - 1) + nums.get(i) >= m) {
                return true;
            }
        }
        return false;
    }
}
