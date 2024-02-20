package com.alibaba.weekly.w385;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/18 17:03
 */
public class leetcode100217 {

    // 枚举顺时针的八个方向
    private static final int[][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {

                for (int[] d : dirs) {
                    int x = i + d[0];
                    int y = j + d[1];
                    int v = mat[i][j];

                    while (x >= 0 && x < m && y >= 0 && y < n) {
                        v = v * 10 + mat[x][y];
                        if (isPrime(v)) {
                            cnt.merge(v, 1, Integer::sum);
                        }
                        x += d[0];
                        y += d[1];
                    }
                }
            }
        }

        int ans = -1;
        int maxCnt = 0;
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int v = e.getKey();
            int c = e.getValue();
            if (c > maxCnt) {
                ans = v;
                maxCnt = c;
            } else if (c == maxCnt) {
                ans = Math.max(ans, v);
            }
        }
        return ans;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
