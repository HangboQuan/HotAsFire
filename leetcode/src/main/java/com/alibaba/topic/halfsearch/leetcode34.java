package com.alibaba.topic.halfsearch;
public class leetcode34 {

    public static void main(String[] args) {
        // nums = [5,7,7,8,8,10], target = 8
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 9;
        int[] res = searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        int start = lowBound(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        int end = lowBound(nums, target + 1) - 1;
        return new int[]{start, end};

    }

    public static int lowBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
