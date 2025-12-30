package com.alibaba.javabase.game;

import java.util.Arrays;

public class SudokuBoard {

    public static void main(String[] args) {
        int[][] arr = new int[9][9];
//        dfs(arr, 0, 0);
        dfs2(arr, 0, 0);
    }

    static boolean solved = false;
    static int len = 9;

    public static void dfs(int[][] arr, int i, int j) {
        if (solved) {
            return ;
        }
        // 找到一个解
        if (i == len) {
            solved = true;
            for (int[] a : arr) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println();
            System.out.println();
            return ;
        }

        // 1.计算下一个位置
        int nextI = i;
        int nextJ = j + 1;
        if (nextJ == len) {
            nextI = i + 1;
            nextJ = 0;
        }

        // 2. 遍历没有值进行放置
        for (int x = 1; x <= 9; x++) {
            // 3. 判断arr[i][j]的位置是否可以放置x
            if (canBePlaced(arr, i, j, x)) {
                // 4. 放置
                arr[i][j] = x;
                // 5. 去给下一个位置arr[nextI][nextJ]存放
                dfs(arr, nextI, nextJ);
                if (solved) {
                    return ;
                }
                // 6. 无法防止则回溯，对应的值清零
                arr[i][j] = 0;
            }
        }
    }

    public static boolean canBePlaced(int[][] arr, int i, int j, int x) {
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

    public static void dfs2(int[][] arr, int i, int j) {
        if (i == len) {
            // 寻找单个解已经结束
            for (int[] a : arr) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println();
            System.out.println();
            return ;
        }
        int nextI = i;
        int nextJ = j + 1;
        if (nextJ == len) {
            nextI = i + 1;
            nextJ = 0;
        }

        for (int x = 1; x <= 9; x++) {
            if (canBePlaced(arr, i, j, x)) {
                arr[i][j] = x;
                dfs2(arr, nextI, nextJ);
                arr[i][j] = 0;
            }
        }
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
}
