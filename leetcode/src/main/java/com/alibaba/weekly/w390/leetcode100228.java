package com.alibaba.weekly.w390;

/**
 * @author quanhangbo
 * @date 2024-03-24 17:11
 */
public class leetcode100228 {

    public int minOperations(int k) {
        if (k == 1) {
            return 0;
        }
        if (k == 2) {
            return 1;
        }
        int a = (int)Math.sqrt(k);

        if (!(Math.sqrt(k) * Math.sqrt(k) == k)) {
            a += 1;
        }

        int b = k / a;
        if (k % a == 0) {
            return a + b - 2;
        }
        return a + b - 1;
    }
}
