package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2023/10/14 12:10
 */
public class 移动零 {

    public static void main(String[] args) {

    }

    public void moveZeroes(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length; i ++ ) {
            if (nums[i] != 0) {
                swap(nums, i, cur);
                cur ++;
            }
        }
    }

    /**
     * left指向已经处理好数组的尾部
     * right指向当前待交换的头部
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left ++;
            }
            right ++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
