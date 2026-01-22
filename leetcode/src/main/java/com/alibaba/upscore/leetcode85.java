package com.alibaba.upscore;

import java.util.Stack;

public class leetcode85 {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
//        System.out.println(maximalRectangle(matrix));
//
//        System.out.println(validMatrix(matrix, 1, 4, 2));
    }

    /**
     * todo: 原因分析（做不出来）
     * 1. 现在的逻辑是枚举左边界i，右边界j，和下边界k，然后判断这个矩阵是否合法 算法复杂度为O(n^4)
     * 2. (j - i) * (k - i) 也是错误的信号，将i作为三个维度用，逻辑混乱，越改越乱，本质上应该是（right - left + 1） * (bottom - top + 1)
     */
//    public static int maximalRectangle(char[][] matrix) {
//        int ans = 0;
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = i; j < matrix[0].length; j++) {
//                for (int k = i; k < matrix.length; k++) {
//                    if (validMatrix(matrix, i, j, k)) {
//                        if ((j - i) * (k - i) > ans) {
//                            System.out.println(i + "," + j + "," + k);
//                        }
//                        ans = Math.max(ans, (j - i) * (k - i));
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//
//
//
//    public static boolean validMatrix(char[][] matrix, int left, int right, int row) {
//        for (int j = left; j <= row; j++) {
//            for (int i = left; i <= right; i++) {
//                if (matrix[j][i] == '0') {
//                    return false;
//                }
//            }
//        }
//
//        System.out.println(left + " " + right + " " + row);
//        return true;
//    }
//
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0) {
            return 0;
        }
        int[] h = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    h[j]++;
                } else {
                    h[j] = 0;
                }
            }
            max = Math.max(max, laragestArea(matrix, h));
        }
        return max;
    }

    public int laragestArea(char[][] matrix, int[] h) {
        Stack<Integer> s = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i <= h.length; i++) {
            int k = i == h.length ? 0 : h[i];

            while (!s.isEmpty() && k < h[s.peek()]) {
                int height = h[s.pop()];
                int width = s.isEmpty() ? i : i - s.peek() - 1;
                max = Math.max(max, height * width);
            }
            s.push(i);
        }
        return max;
    }

}
