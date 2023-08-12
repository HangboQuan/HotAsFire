package com.alibaba.topic.unionFind;

/**
 * @author quanhangbo
 * @date 2023/8/12 18:32
 */
public class numOfLand {

    static class UnionFind {

        // 连通分量
        private int count;
        // 节点的祖宗节点
        private int[] parent;
        // 节点所在集合的大小
        private int[] size;

        public UnionFind(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 返回x的根节点 + 路径压缩优化
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // 合并的时候 尽可能平衡些
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return ;
            }
            if (size[rootP] > size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            this.count --;
        }


        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int count() {
            return this.count;
        }

    }

    public static void main(String[] args) {

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {-1, 0, 1, 0};
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        for (int i = 0; i < grid.length; i ++ ) {
            for (int j = 0; j < grid[0].length; j ++ ) {
                if (isLand(grid, i, j)) {
                    new UnionFind(grid.length * grid[0].length);

                }
            }
        }

    }

    public static boolean isLand(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] == 0) {
            return false;
        }
        return true;
    }
}
