package com.alibaba.weekly.W392;

/**
 * @author quanhangbo
 * @date 2024-04-19 23:03
 */
public class leetcode3115 {

    public int maximumPrimeDifference(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            if (isPrimer(nums[start])) {
                break;
            }
            start ++;
        }

        while (start <= end) {
            if (isPrimer(nums[end])) {
                break;
            }
            end --;
        }
        return end - start;
    }

    public boolean isPrimer(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i ++ ) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrimer2(int num) {
        for (int i = 2; i * i <= num; i ++) {
            if (num % i == 0) {
                return false;
            }
        }
        return num >= 2;
    }
}
