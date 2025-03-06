package com.alibaba.leetcode_hot_100;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2025-03-05 22:50
 */
public class 二分 {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] ans = searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length <= 0) {
            return new int[]{-1, -1};
        }
        int leftNode = searchLeft(nums, target);
        int rightNode = searchRight(nums, target);
        return new int[]{leftNode, rightNode};
    }

    public static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    public static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }
}
