package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024/3/21 9:38
 */
public class 层数最深叶子节点的和 {

    int ans = 0;
    public int deepestLeavesSum(TreeNode root) {
        // 首先计算出二叉树的最大深度
        int depth = depthTree(root);
        depthTraves(root, depth, 0);
        return ans;
    }

    public void depthTraves(TreeNode root, int depth, int cur) {
        if (root == null) {
            return ;
        }
        if (root.left == null && root.right == null) {
            if (cur == depth - 1) {
                ans += root.val;
            }
            return ;
        }
        depthTraves(root.left, depth, cur + 1);
        depthTraves(root.right, depth, cur + 1);
    }

    public int depthTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depthTree(root.left);
        int right = depthTree(root.right);
        return Math.max(left, right) + 1;
    }
}
