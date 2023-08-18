package com.alibaba.topic.dfs;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/8/11 15:18
 */
public class numbersOfLand {
    // 岛屿数量
    public static int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int nums = 0;
        Set<String> hashSet = new HashSet<>();
        for (int i = 0; i < row; i ++ ) {
            for (int j = 0; j < col; j ++ ) {
                if (grid[i][j] == '1') {
                    nums ++;
                    isLand(grid, i, j, hashSet);
                }
            }
        }
        return nums;
    }


    public static void isLand(char[][] grid, int row, int col, Set<String> set) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return ;
        }
        grid[row][col] = '0';
        isLand(grid, row - 1, col, set);
        isLand(grid, row, col - 1, set);
        isLand(grid, row + 1, col, set);
        isLand(grid, row, col + 1, set);
    }


    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid));
    }
}


