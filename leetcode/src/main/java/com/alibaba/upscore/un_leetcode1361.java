package com.alibaba.upscore;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/7 16:05
 */
public class un_leetcode1361 {

    public static void main(String[] args) {

    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // parent 统计每个节点的出度
        int[] parent = new int[n];
        for (int i = 0; i < n; i ++ ) {
            if (leftChild[i] != -1) {
                parent[leftChild[i]] ++;
            }
            if (rightChild[i] != -1) {
                parent[rightChild[i]] ++;
            }
        }
        int root = -1;
        for (int i = 0; i < n; i ++ ) {
            if (parent[i] == 0) {
                // 找到根节点
                root = i;
                break;
            }
        }
        if (root == -1) {
            return false;
        }
        // 广搜
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(root);
        set.add(root);
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            // 左子树不为空
            if (leftChild[temp] != -1) {
                // 左子节点 被访问过
                if (set.contains(leftChild[temp])) {
                    return false;
                }
                queue.offer(leftChild[temp]);
                set.add(leftChild[temp]);
            }

            // 右子树不为空
            if (rightChild[temp] != -1) {
                // 右子节点 被访问过
                if (set.contains(rightChild[temp])) {
                    return false;
                }
                queue.offer(rightChild[temp]);
                set.add(rightChild[temp]);
            }
        }
        return set.size() == n;

    }
}
