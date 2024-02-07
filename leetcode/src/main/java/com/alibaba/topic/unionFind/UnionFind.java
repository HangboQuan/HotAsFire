package com.alibaba.topic.unionFind;

/**
 * @author quanhangbo
 * @date 2024/2/7 15:03
 */
public class UnionFind {

    // 当前节点x的父节点
    private int[] parent;

    // 连通分量的个数
    private int count;

    // 图中节点的个数
    public UnionFind(int n) {
        parent = new int[n];
        this.count = count;
        // 初始情况下自己的父亲节点指向自己
        for (int i = 0; i < n; i ++ ) {
            parent[i] = i;
        }
    }

    // 合并
    public void union(int p, int q) {
        int pPar = find(p);
        int qPar = find(q);
        parent[pPar] = qPar;
        this.count --;
    }

    // 查询 时间复杂度主要取决于find方法 find方法的时间复杂度 等价于树的高度
    public int find(int x) {
        while (parent[x] != x) {
            // 路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // 连通
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
