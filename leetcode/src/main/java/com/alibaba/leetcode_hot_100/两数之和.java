package com.alibaba.leetcode_hot_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2023/10/10 21:28
 */
public class 两数之和 {

    public int[] twoSum(int[] nums, int target) {
        // int[] res = new int[2];
        // for (int i = 0; i < nums.length; i ++ ) {
        //     for (int j = i + 1; j < nums.length; j ++ ) {
        //         if (nums[i] + nums[j] == target) {
        //             res[0] = i;
        //             res[1] = j;
        //             break;
        //         }
        //     }
        // }
        // return res;


        // 时间复杂度O(n), 空间复杂度O(n)
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i ++ ) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
