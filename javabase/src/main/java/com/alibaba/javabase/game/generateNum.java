package com.alibaba.javabase.game;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/3/15 18:17
 */
public class generateNum {

    static int[][] ans = new int[9][9]; // 初始化一个9 * 9的矩阵
    public static void main(String[] args) {

        boolean[] col = new boolean[9];



    }

    public static boolean fillRow(int row) { // 第几行
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i ++ ) {
            set.add(ans[row][i]);
        }
        return set.size() == 9;
    }

    public static boolean fillCol(int col) { // 第几列
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i ++ ) {
            set.add(ans[i][col]);
        }
        return set.size() == 9;
    }

    public static boolean fillMatrix(int row, int col) {

    }

}
