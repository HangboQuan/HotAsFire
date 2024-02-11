package com.alibaba.weekly.w384;

/**
 * @author quanhangbo
 * @date 2024/2/11 17:34
 */
public class leetcode100230 {
    public int[][] modifiedMatrix(int[][] matrix) {
        int[] col = new int[matrix[0].length];
        for (int j = 0; j < col.length; j ++ ) {
            int maxCol = 0;
            for (int i = 0; i < matrix.length; i ++ ) {
                maxCol = Math.max(maxCol, matrix[i][j]);
            }
            col[j] = maxCol;
        }

        for (int i = 0; i < matrix.length; i ++ ) {
            for (int j = 0; j < matrix[0].length; j ++ ) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = col[j];
                }
            }
        }
        return matrix;

    }
}
