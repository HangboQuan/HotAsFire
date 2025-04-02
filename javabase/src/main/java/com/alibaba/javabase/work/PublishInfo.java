package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:28
 */
public class PublishInfo {

    // [1,3,5,6]
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(getIndex(nums, 3));
        System.out.println(getIndex(nums, 2));
        System.out.println(getIndex(nums, 7));
    }

    public static int getIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                ans =  mid;
                right = mid - 1;
            }
        }
        return ans;
    }


}
