package com.alibaba.weekly.w358;

/**
 * @author quanhangbo
 * @date 2023/8/13 15:35
 */
public class leetcode6939 {

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{51, 71, 17, 24, 42}));
    }

    public static int maxSum(int[] nums) {
        int[] maxNum = new int[nums.length];

        for (int i = 0; i < nums.length; i ++ ) {
            maxNum[i] = maxTarget(nums[i]);
        }

        int max = -1;
        for (int i = 0; i < nums.length - 1; i ++ ) {
            for (int j = i + 1; j < nums.length; j ++ ) {
                if (maxNum[i] == maxNum[j] && nums[i] + nums[j] > max) {
                    max = nums[i] + nums[j];
                }
            }
        }
        return max;
    }

    public static int maxTarget(int target) {
        int max = 0;
        while (target > 0) {
            int temp = target % 10;
            if (temp > max) {
                max = temp;
            }
            target /= 10;
        }

        return max;
    }
}
