package com.alibaba.javabase.game;

/**
 * 生成数独面板
 */
public class GenerateSudokuBoard {

    public static void main(String[] args) {
        int[][] arr = new int[9][9];
        generateSudokuBoard(arr, 0, 0);
    }

    public static void printMatrix(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean canBeReplaced(int[][] arr, int i, int j, int x) {
        for (int m = 0; m < arr[0].length; m++) {
            // 每一列 出现重复值
            if (arr[i][m] == x) {
                return false;
            }
        }

        for (int m = 0; m < arr.length; m++) {
            // 每一行 出现重复值
            if (arr[m][j] == x) {
                return false;
            }
        }

        int indexI = matrixThreeIndex(i);
        int indexJ = matrixThreeIndex(j);
        for (int n = indexI; n < indexI + 3; n++) {
            for (int o = indexJ; o < indexJ + 3; o++) {
                // 3 * 3矩阵 出现重复值
                if (arr[n][o] == x) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int matrixThreeIndex(int i) {
        int index = 0;
        if (i >= 3 && i <= 5) {
            index = 3;
        } else if (i >= 6 && i <= 8) {
            index = 6;
        }
        return index;
    }

    public static void generateSudokuBoard(int[][] arr, int i, int j) {
        // 结束条件
        if (i == 9) {
            printMatrix(arr);
            return ;
        }
        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;

        for (int x = 1; x <= 9; x++) {
            if (canBeReplaced(arr, i, j, x)) {
                // 放
                arr[i][j] = x;
                // 继续放下一个
                generateSudokuBoard(arr, nextI, nextJ);
                // 放不了的话，回溯
                arr[i][j] = 0;
            }
        }
    }
}
