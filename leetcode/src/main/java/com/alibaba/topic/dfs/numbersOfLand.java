package com.alibaba.topic.dfs;

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
        for (int i = 0; i < row; i ++ ) {
            for (int j = 0; j < col; j ++ ) {
                if (grid[i][j] == '1') {
//                    max = Math.max(max, isLand(grid, i, j));
                    nums ++;
                    isLand(grid, i, j);
                }
            }
        }
        return nums;
    }

//    public static int isLand(char[][] grid, int row, int col) {
////        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '1') {
////            return 0;
////        }
//
//        if (!(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length)) {
//            return 0;
//        }
//
//        if (grid[row][col] != '1') {
//            return 0;
//        }
//        grid[row][col] = '2';
//        return 1 + isLand(grid, row - 1, col) +
//                isLand(grid, row, col - 1) +
//                isLand(grid, row + 1, col) +
//                isLand(grid, row, col + 1);
//    }

    public static void isLand(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return ;
        }
        grid[row][col] = '0';
        isLand(grid, row - 1, col);
        isLand(grid, row, col - 1);
        isLand(grid, row + 1, col);
        isLand(grid, row, col + 1);
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

class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int value;

    public TreeNode(int value) {
        this.value = value;
    }

}
