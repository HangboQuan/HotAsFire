package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2024/1/31 10:33
 */
public class 矩阵置零 {

    public static void main(String[] args) {

    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i ++ ) {
            for (int j = 0; j < n; j ++ ) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }


        for (int i = 0; i < m; i ++ ) {
            for (int j = 0; j < n; j ++ ) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
