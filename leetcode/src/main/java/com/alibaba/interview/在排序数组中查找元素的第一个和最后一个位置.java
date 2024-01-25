package com.alibaba.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/9/28 23:13
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {

    // 时间复杂度O(logN)
    public int[] searchRange(int[] nums, int target) {
        if (nums.length <= 0) {
            return new int[]{-1, -1};
        }
        int left = leftBorder(nums, target);
        int right = rightBorder(nums, target);
        return new int[]{left, right};

    }

    public int leftBorder(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[l] != target) {
            return -1;
        }
        return l;
    }

    public int rightBorder(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if (nums[r] != target) {
            return -1;
        }
        return r;
    }


    public static List<List<Integer>> threeSum(int[] nums){
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++ ) {
            int target = nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = target + nums[j] + nums[k];
                if (sum > 0) {
                    k --;
                } else if (sum < 0) {
                    j ++;
                } else {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                    if (!result.contains(list)) {
                        result.add(list);
                    }

                    j ++;
                    k --;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> res = threeSum(nums);
        for (List<Integer> list : res) {
            System.out.println(list.toString());
        }
    }
}
