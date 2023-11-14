package com.alibaba.score;


/**
 * @author quanhangbo
 * @date 23-11-14 上午11:19
 */
public class 数组的最小相等和 {

    public static long minSum(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        int zero1 = 0;
        int zero2 = 0;
        for (int i = 0; i < nums1.length; i ++ ) {
            if (nums1[i] == 0) {
                zero1 ++;
            }
            sum1 += nums1[i];
        }

        for (int i = 0; i < nums2.length; i ++ ) {
            if (nums2[i] == 0) {
                zero2 ++;
            }
            sum2 += nums2[i];
        }

        if (zero1 == 0 && sum2 + zero2 > sum1) {
            return -1;
        }

        if (zero2 == 0 && sum1 + zero1 > sum2) {
            return -1;
        }
        return sum2 + zero2 > sum1 + zero1 ? sum2 + zero2 : sum1 + zero1;
    }

    public static void main(String[] args) {
        int[] num1 = {10, 4, 24, 2, 17, 10, 0, 29, 3, 0, 19, 7, 24, 17, 30, 7};
        int[] num2 = {1, 0, 13, 0, 0, 28, 0, 23, 24, 13, 4, 30, 8, 4, 0, 1, 29, 25};
        System.out.println(minSum(num1, num2));
    }
}