package com.alibaba.weekly.W392;

/**
 * @author quanhangbo
 * @date 2024-06-22 22:30
 */
public class leetcode3185 {

    public static void main(String[] args) {
        System.out.println(countCompleteDayPairs(new int[]{12, 12, 30, 24, 24}));
        System.out.println(minMatrix(3, 3, new int[][]{{1, 2, 3}, {2, 3, 4}, {4, 5, 6}}));
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

    public static int minMatrix(int m, int n, int[][] arr) {
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];
        for (int j = 1; j < n; j++) {
            dp[j][0] = dp[j - 1][0] + arr[j][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
