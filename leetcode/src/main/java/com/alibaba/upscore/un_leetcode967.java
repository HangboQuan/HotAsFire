package com.alibaba.upscore;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/8 15:36
 */
public class un_leetcode967 {

    public static void main(String[] args) {

    }

    public int[] numsSameConsecDiff(int n, int k) {
        // 构造答案
        Set<Integer> cur = new HashSet<>();
        for (int i = 1; i <= 9; i ++ ) {
            cur.add(i);
        }

        for (int steps = 1; steps <= n - 1; steps ++ ) {
            Set<Integer> cur2 = new HashSet<>();
            for (int x : cur) {
                int d = x % 10;
                if (d - k >= 0) {
                    cur2.add(10 * x + (d - k));
                }
                if (d + k <= 9) {
                    cur2.add(10 * x + (d + k));
                }
            }
            cur = cur2;
        }

        if (n == 1) {
            cur.add(0);
        }
        int[] ans = new int[cur.size()];
        int index = 0;
        for (int x : cur) {
            ans[index ++] = x;
        }
        return ans;
    }
}
