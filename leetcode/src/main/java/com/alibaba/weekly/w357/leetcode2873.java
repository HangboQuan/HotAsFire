package com.alibaba.weekly.w357;

/**
 * @author quanhangbo
 * @date 2023/10/1 10:29
 */
public class leetcode2873 {

    public static void main(String[] args) {
        System.out.println(maximumTripletValue(new int[]{1000000, 1, 1000000}));
    }

    public static long maximumTripletValue(int[] nums) {
        long max = 0L;
        for (int i = 0; i < nums.length; i ++ ) {
            for (int j = i + 1; j < nums.length; j ++ ) {
                for (int k = j + 1;  k < nums.length; k ++ ) {
                    max = Math.max(max, (long)(nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return max;
    }

}
