package com.alibaba.upscore;

/**
 * @author quanhangbo
 * @date 2024/2/8 15:36
 */
public class un_leetcode1493 {
    public static void main(String[] args) {

    }

    public int longestSubarray(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        for (int i = 0; i < nums.length; i ++ ) {
            int l = i - 1;
            int r = i + 1;
            int lcur = 0;
            int rcur = 0;
            while (l >= 0 && nums[l] == 1) {
                lcur ++;
                l --;
            }

            while (r < nums.length && nums[r] == 1) {
                rcur ++;
                r ++;
            }
            left[i] = lcur;
            right[i] = rcur;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i ++ ) {
            max = Math.max(max, left[i] + right[i]);
        }
        return max;

    }
}
