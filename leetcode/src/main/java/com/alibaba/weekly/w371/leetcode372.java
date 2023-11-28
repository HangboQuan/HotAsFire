package com.alibaba.weekly.w371;

/**
 * @author quanhangbo
 * @date 2023/11/19 16:01
 */
public class leetcode372 {


    public long minimumSteps(String s) {
        long res = 0;
        int index = 0;
        for (int i = s.length() - 1; i >= 0; i -- ) {
            if (s.charAt(i) == '0') {
                index ++;
            } else {
                res += index;
            }
        }
        return res;
    }

    public int findMinimumOperations(String s1, String s2, String s3) {
        int res = 0;
        int min = Math.min(s1.length(), Math.min(s2.length(), s3.length()));
        int index = 0;

        for (int i = 0; i < min; i ++ ) {
            if (s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i)) {
                index ++;
            } else {
                break;
            }
        }

        if (index >= 1) {
            res += (s1.length() - index) + (s2.length() - index) + (s3.length() - index);
            return res;
        } {
            return -1;
        }

    }

    public int maximumXorProduct(long a, long b, int n) {
        return 0;
    }



}
