package com.alibaba.leetcode_hot_100;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2023/10/17 17:21
 */
public class 和为K的子数组 {

    // 前缀和 + hashmap
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int pre = 0, count = 0;
        for (int i = 0; i < nums.length; i ++ ) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    // 前缀和 暴力
    public int subarraySum2(int[] nums, int k) {
        // 前缀和
        int res = 0;
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i ++ ) {
            presum[i + 1] = presum[i] + nums[i];
        }

        for (int i = presum.length - 1; i >= 0; i -- ) {
            for (int j = 0; j < i; j ++ ) {
                if (presum[i] - presum[j] == k) {
                    res ++;
                }
            }
        }
        return res;
    }

    // 纯暴力
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = nums.length - 1; i >= 0; i -- ) {
            int sum = 0;
            for (int j = i; j >= 0; j -- ) {
                sum += nums[j];
                if (sum == k) {
                    res ++;
                }
            }
        }
        return res;
    }
}
