package com.alibaba.javabase.game;

import org.apache.commons.lang3.tuple.Pair;
import sun.lwawt.macosx.CThreading;

import java.util.Arrays;
import java.util.Random;

/**
 * 生成一个挖洞的数独面板
 */
public class GenerateSudokuBoardGame {

    /**
     * 数独解的数量（保证唯一解）
     */
    static int uniqueSolve = 0;
    public static void main(String[] args) {
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
        // 传两个参数，数组本身和挖洞数量 通过挖洞数量来控制游戏难度
        generateGame(result, 15);
        for (int[] a : result) {
            System.out.println(Arrays.toString(a));
        }
    }

    // todo：问题转换为将一个数组元素挖洞之后，是否存在唯一解
    public static void generateGame(int[][] result, int threshold) {
        // 1.挖洞
        deleteSthElement(result, threshold);
        // 2. 判断挖完洞的数独面板是否存在唯一解
        handleGenerateGame(result, 0, 0);
    }



    public static void deleteSthElement(int[][] result, int threshold) {
        // 随机生成一个数组下标，将其挖空
        int index = 0;
        while (index < threshold) {
            int i = getRandomIndex();
            int j = getRandomIndex();

            int x = result[i][j];
            // 该位置是有值的
            if (x != 0) {
                // 挖空
                result[i][j] = 0;
                // 判断其是否存在唯一解
                if (uniqueSudokuBoard(result)) {
                    index++;
                } else {
                    result[i][j] = x;
                }
            }
        }
    }

    public static boolean uniqueSudokuBoard(int[][] result) {
        // 每挖空一个元素，这里都必须重置，不然全局变量会一直累积
        uniqueSolve = 0;
        // 从头开始判断，挖空index+1元素后，是否存在唯一解
        handleGenerateGame(result, 0, 0);
        return uniqueSolve == 1;
    }

    public static void handleGenerateGame(int[][] result, int i, int j) {
        // 结束条件
        if (uniqueSolve > 1) {
            return ;
        }
        if (i == 9) {
            uniqueSolve++;
            return ;
        }
        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;

        // 当前位置被重置过了，直接判断下一个位置是否存在唯一解
        if (result[i][j] != 0) {
            handleGenerateGame(result, nextI, nextJ);
        }


        for (int x = 1; x <= 9; x++) {
            // 当前的元素可以被放置
            if (canBeReplace(result, i, j, x)) {
                result[i][j] = x;
                handleGenerateGame(result, nextI, nextJ);
                result[i][j] = 0;
            }
        }
    }

    public static boolean canBeReplace(int[][] arr, int i, int j, int x) {
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

    public static int getRandomIndex() {
        return new Random().nextInt(9);
    }

}
