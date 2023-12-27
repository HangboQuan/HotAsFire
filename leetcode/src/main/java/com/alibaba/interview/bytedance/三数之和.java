package com.alibaba.interview.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/12/27 19:55
 */
public class 三数之和 {

    /**
     * 三数之和 排序 + 双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++ ) {
            int target = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = target + nums[j] + nums[k];
                if (sum < 0) {
                    j ++;
                } else if (sum > 0) {
                    k --;
                } else {
                    ans.add(Arrays.asList(target, nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j ++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k --;
                    }
                    j ++;
                    k --;
                }
            }
        }
        return ans;
    }
}
