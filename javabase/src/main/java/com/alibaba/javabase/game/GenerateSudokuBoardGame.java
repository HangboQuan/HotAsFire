package com.alibaba.javabase.game;

import java.util.Arrays;
import java.util.HashMap;
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
        generateGame(result, 35);
//        for (int[] a : result) {
//            System.out.println(Arrays.toString(a));
//        }
        System.out.println("====================");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // todo：问题转换为将一个数组元素挖洞之后，是否存在唯一解
    public static void generateGame(int[][] result, int threshold) {
        // 1.挖洞
        deleteSthElement(result, threshold);
        // 2. 判断挖完洞的数独面板是否存在唯一解
        handleGenerateGame(result, 0, 0);
    }

    /**
     * 生成一个新的数独游戏
     * @param difficulty 难度：简单(20-25), 中等(30-40), 困难(45-55)
     * @return 数独面板，0表示空白
     */
    public static int[][] generateNewGame(String difficulty) {
        // 根据难度确定挖洞数量
        int threshold;
        Random random = new Random();
        switch (difficulty.toLowerCase()) {
            case "easy":
            case "简单":
                // 20-25
                threshold = 20 + random.nextInt(6);
                break;
            case "medium":
            case "中等":
                // 30-40
                // todo: 该值暂定为15
                threshold = 30 + random.nextInt(11);
                break;
            case "hard":
            case "困难":
                // 45-55
                threshold = 45 + random.nextInt(11);
                break;
            default:
                threshold = 25 + random.nextInt(11);
        }
        
        // 使用一个有效的数独模板
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

        // 深拷贝数组以避免修改原数组
//        int[][] game = new int[9][9];
//        for (int i = 0; i < 9; i++) {
//            System.arraycopy(result[i], 0, game[i], 0, 9);
//        }
        System.out.println("threshold:" + threshold);
        // 生成游戏（挖洞）
        generateGame(result, threshold);
        for (int[] a : result) {
            System.out.println(Arrays.toString(a));
        }


        // todo: 这里的问题是长时间生成不了，先暂时mock下
//        int[][] game = {
//                {1, 2, 3, 4, 5, 6, 0, 0, 0},
//                {4, 5, 0, 7, 8, 9, 1, 0, 0},
//                {7, 8, 9, 1, 2, 3, 4, 5, 6},
//                {2, 1, 4, 0, 0, 5, 8, 0, 0},
//                {3, 7, 8, 0, 1, 0, 6, 0, 5},
//                {6, 9, 5, 8, 0, 4, 3, 1, 2},
//                {5, 4, 0, 2, 3, 7, 9, 6, 8},
//                {0, 6, 7, 5, 4, 8, 2, 3, 1},
//                {0, 3, 2, 6, 9, 1, 5, 7, 4}
//        };
        int[][] game = new int[9][9];
        game = result;
        return game;
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
            return ;
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
