package com.alibaba.leetcode_hot_100;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/10/14 19:45
 */
public class 三数之和 {

    /**
     * 时间复杂度O(n^2)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++ ) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = target + nums[j] + nums[k];
                if (sum < 0) {
                    j ++;
                } else if (sum > 0) {
                    k --;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(target, nums[j], nums[k])));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j ++;
                    }
                    while (k > j && nums[k] == nums[k - 1]) {
                        k --;
                    }
                    j ++;
                    k --;
                }
            }
        }
        return res;
    }


    /**
     * 时间复杂度O(n^3 * logn)
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum1(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++ ) {
            for (int j = i + 1; j < nums.length; j ++ ) {
                for (int k = j + 1; k < nums.length; k ++ ) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> ans = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        Collections.sort(ans);
                        if (!res.contains(ans)) {
                            res.add(ans);
                        }
                    }
                }
            }
        }
        return res;
    }
}
