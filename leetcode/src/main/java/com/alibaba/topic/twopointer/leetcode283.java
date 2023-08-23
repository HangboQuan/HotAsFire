package com.alibaba.topic.twopointer;

/**
 * @author quanhangbo
 * @date 2023/8/23 20:51
 */
public class leetcode283 {

    public static void main(String[] args) {
        int[] ans = {0, 1, 0, 3, 12};
        moveZeroes(ans);

        for (int i = 0; i < ans.length; i ++ ) {
            System.out.println(ans[i]);
        }

    }

    public static void moveZeroes(int[] nums) {
        for (int i = nums.length - 1, j = nums.length - 1; i >= 0; ) {
            if (nums[j] == 0) {
                j --;
            }
            if (nums[i] == 0) {
                swap(nums, i --, j);
            } else {
                i --;
            }
        }
    }

    public static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
