package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024/3/21 14:45
 */
public class 把二叉搜索树转换为累加树 {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
