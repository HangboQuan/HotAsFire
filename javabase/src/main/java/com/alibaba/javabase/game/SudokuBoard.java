package com.alibaba.javabase.game;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {

    public static void main(String[] args) {
        int[][] arr = new int[9][9];
//        dfs(arr, 0, 0);
//        dfs2(arr, 0, 0);

        // todo：现在给定一个数独拼盘，我现在要求挖去一些元素，最终保证挖去元素之后，还存在唯一解
        int[][] result = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 7, 8, 9, 1, 2, 6, 4, 5},
                {6, 9, 5, 8, 7, 4, 3, 1, 2},
                {5, 4, 1, 2, 3, 7, 9, 6, 8},
                {9, 6, 7, 5, 4, 8, 2, 3, 1},
                {8, 3, 2, 6, 9, 1, 5, 7, 4}
        };
        /**
         * 15: 挖15个洞
         */
        generateSudoku2(result, 50);

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

//        hasUniqueSolution(result);
    }

    /**
     * 返回值>=0&&<=8
     * @return
     */
    public static int getRandomIndex() {
        return new Random().nextInt(9);
    }


    static int count = 15;
    public static void generateSudoku(int[][] result, int countThreshold) {
        // todo: 这里不应该再用递归，而是应该用while
        if (countThreshold >= count) {
            for (int[] a : result) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println();
        }
        // 1. 随机获取下标值，开始挖洞
        int i = getRandomIndex();
        int j = getRandomIndex();
        int x = result[i][j];
        if (x != 0) {
            // 还没有被挖洞，就直接给他挖了
            result[i][j] = 0;
            // 判断被挖之后的数组能否被填充为唯一解
            boolean uniqueSudo = dfs3(result, i, j);
            // 挖洞成功
            if (uniqueSudo) {
                generateSudoku(result, countThreshold + 1);
                // 挖洞失败之后，重新把值赋值
                result[i][j] = x;
            }
        }
    }

    public static void generateSudoku2(int[][] result, int countThreshold) {
        int index = 0;
        while (index < countThreshold) {
            // 1. 随机获取下标值，开始挖洞
            int i = getRandomIndex();
            int j = getRandomIndex();
            int x = result[i][j];
            if (x != 0) {
                // 还没有被挖洞，就直接给他挖了
                result[i][j] = 0;
                // 判断被挖之后的数组能否被填充为唯一解
                // todo: 一定要从（0，0）开始遍历
                countThread = 0;
                boolean uniqueSudo = hasUniqueSolution(result);
                // 挖洞成功
                if (uniqueSudo) {
                    index++;
                } else {
                    // 回溯
                    result[i][j] = x;
                }
            }
        }

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


    static int countThread;
    public static boolean dfs3(int[][] arr, int i, int j) {
        // 超过一个结果，直接返回
        if (countThread > 1) {
            return false;
        }
        if (i == len) {
            for (int m = 0; m < arr.length; m++) {
                for (int n = 0; n < arr[0].length; n++) {
                    System.out.print(arr[m][n] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
            countThread++;
            return countThread <= 1;
        }
        int nextI = i;
        int nextJ = j + 1;
        if (nextJ == len) {
            nextI = i + 1;
            nextJ = 0;
        }

        if (arr[i][j] != 0) {
            // 直接进行下一步
            return dfs3(arr, nextI, nextJ);
        }

        for (int x = 1; x <= 9; x++) {
            if (canBePlaced(arr, i, j, x)) {
                arr[i][j] = x;
                dfs3(arr, nextI, nextJ);
                arr[i][j] = 0;
            }
        }
        return true;
    }

    public static void dfs2(int[][] arr, int i, int j) {
        if (i == len) {
            // 寻找单个解已经结束
//            for (int[] a : arr) {
//                System.out.println(Arrays.toString(a));
//            }
            for (int m = 0; m < arr.length; m++) {
                for (int n = 0; n < arr[0].length; n++) {
                    System.out.print(arr[m][n] + " ");
                }
                System.out.println();
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

    static int solutionCount;
    public static boolean hasUniqueSolution(int[][] result) {
        solutionCount = 0;
        solveAndCount(result, 0, 0);
        return solutionCount == 1;
    }


    public static void solveAndCount(int[][] result, int i, int j) {
        // 找到唯一解
        if (solutionCount > 1) {
            return ;
        }

        // 找到解
        if (i == 9) {
            solutionCount++;
            return ;
        }

        // 下一步
        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;

        // 已经被占用了
        if (result[i][j] != 0) {
            solveAndCount(result, nextI, nextJ);
            return ;
        }

        // 没有被占用
        for (int x = 1; x <= 9; x++) {
            // 可以被放置
            if (canBePlaced(result, i, j, x)) {
                result[i][j] = x;
                solveAndCount(result, nextI, nextJ);
                result[i][j] = 0;
            }
        }
    }
}
