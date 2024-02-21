package com.alibaba.leetcode_top_100;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2024/2/21 23:19
 */
public class leetcode1两数之和 {

    public static void main(String[] args) {

    }

    // hashtable
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++ ) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
