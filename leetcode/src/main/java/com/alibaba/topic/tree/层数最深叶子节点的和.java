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


    // 8.24
    int sum = 0;
    public int deepestLeavesSum2(TreeNode root) {
        // 统计这个树有几层，然后再去找到对应层数的数字求和
        int len = depth(root);
        result(root, 0, len);
        return sum;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left) + 1;
        int right = depth(root.right) + 1;
        return Math.max(left, right);
    }

    public void result(TreeNode root, int level, int len) {
        if (root == null) {
            return ;
        }
        if (level == len - 1) {
            sum += root.val;
            return ;
        }
        result(root.left, level + 1, len);
        result(root.right, level + 1, len);
    }

}
