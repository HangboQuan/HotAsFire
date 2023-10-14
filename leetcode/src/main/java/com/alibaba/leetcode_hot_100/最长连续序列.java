package com.alibaba.leetcode_hot_100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2023/10/14 11:06
 */
public class 最长连续序列 {

    public static void main(String[] args) {

    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i ++ ) {
            if (!set.contains(nums[i] - 1)) {
                // 说明是自从起点开始的
                int curNum = nums[i];
                int count = 1;
                while (set.contains(curNum + 1)) {
                    curNum ++;
                    count ++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
    public static int longestConsecutive1(int[] nums) {
        // 暴力枚举 时间复杂度 O(n^2)
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i ++ ) {
            int index = i;
            int target = nums[index];
            int count = 1;
            while (set.contains(target + 1)) {
                target ++;
                count ++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
