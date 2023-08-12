package com.alibaba.topic.dfs;

/**
 * @author quanhangbo
 * @date 2023/8/12 16:41
 */
public class perimeterOfLand {

    /**
     * 岛屿周长
     * @param args
     */
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        System.out.println(islandPerimeter(grid));

    }

    public static int islandPerimeter(int[][] grid) {

        int max = 0;
        for (int i = 0; i < grid.length; i ++ ) {
            for (int j = 0; j < grid[0].length; j ++ ) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return max;
    }

    public static int dfs(int[][] grid, int i, int j) {
        // 岛屿的周长是岛屿方格和非岛屿方格相邻的边的数量
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 1;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return  dfs(grid, i - 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i + 1, j) +
                dfs(grid, i, j + 1);
    }
}
