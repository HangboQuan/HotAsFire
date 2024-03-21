package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024/3/21 15:09
 */
public class 二叉树剪枝 {
    public TreeNode pruneTree(TreeNode root) {
        if (root != null) {
            root.left = pruneTree(root.left);
            root.right = pruneTree(root.right);
            if (root.left == null && root.right == null && root.val == 0) {
                return null;
            }
        }
        return root;

    }
}
