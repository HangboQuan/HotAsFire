package com.alibaba.topic.dfs;


/**
 * @author quanhangbo
 * @date 2023/8/11 15:18
 */
public class countSubLands {

    static boolean match;

    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int row = grid2.length;
        int col = grid2[0].length;
        int nums = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid2[i][j] == 1) {
                    match = true;
                    isLand(grid1, grid2, i, j);
                    if (match) nums++;

                }
            }
        }

        return nums;
    }


    public static void isLand(int[][] grid1, int[][] grid2, int row, int col) {
        if (row < 0 || row >= grid2.length || col < 0 || col >= grid2[0].length || grid2[row][col] == 0) {
            return;
        }
        if (grid1[row][col] != 1) {
            match = false;
        }
        grid2[row][col] = 0;
        isLand(grid1, grid2, row - 1, col);
        isLand(grid1, grid2, row, col - 1);
        isLand(grid1, grid2, row + 1, col);
        isLand(grid1, grid2, row, col + 1);
    }


    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1}
        };


        int[][] grid2 = {
                {1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0}
        };

        System.out.println(countSubIslands(grid1, grid2));
    }
}

