package com.alibaba.topic.halfsearch;

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
}
