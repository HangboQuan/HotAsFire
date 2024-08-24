package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024-08-24 14:50
 */
public class 装饰树 {
    public TreeNode expandBinaryTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
        }
        if (root.right != null) {
            root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
        }

        return root;
    }
}
