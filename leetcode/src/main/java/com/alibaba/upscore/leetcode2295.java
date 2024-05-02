package com.alibaba.upscore;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2024-03-29 21:38
 */
public class leetcode2295 {

    public static void main(String[] args) {

    }

    public int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++ ) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < operations.length; i ++ ) {
            int k = operations[i][0];
            int v = operations[i][1];
            if (map.containsKey(k)) {
                int index = map.get(k);
                nums[index] = v;
                map.put(nums[index], index);
            }
        }
        return nums;
    }
}
