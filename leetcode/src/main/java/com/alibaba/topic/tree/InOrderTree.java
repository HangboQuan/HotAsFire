package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024/1/21 13:57
 */
public class InOrderTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(9);
        root.right.left.left = new TreeNode(8);


        TreeNode p = root.left.right;

        InOrder(root, p);
    }

    public static TreeNode InOrder(TreeNode root, TreeNode p) {
        if (root == p) {
            return null;
        }
        if (root != null) {
            InOrder(root.left, p);
            System.out.println(root.val);
            InOrder(root.right, p);
        }
        return null;
    }
}
