package com.alibaba.weekly.W392;

/**
 * @author quanhangbo
 * @date 2024-06-22 22:30
 */
public class leetcode3185 {

    public static void main(String[] args) {
        System.out.println(countCompleteDayPairs(new int[]{12, 12, 30, 24, 24}));
    }

    public static long countCompleteDayPairs(int[] hours) {
        long ans = 0;
        int[] cnt = new int[24];
        for (int h : hours) {
            ans += cnt[(24 - h % 24) % 24];
            cnt[h % 24] ++;
        }
        return ans;

    }
}
