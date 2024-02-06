package com.alibaba.upscore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/6 17:49
 */
public class leetcode945 {
    public static void main(String[] args) {

    }

    public int minIncrementForUnique(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        int res = 0;
        int max = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                res += max - num + 1;
                num = max + 1;
            }
            set.add(num);
            max = Math.max(max, num);
        }
        return res;
    }
}
