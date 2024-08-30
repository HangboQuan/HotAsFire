package com.alibaba.topic.tree;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024-08-29 19:32
 */
public class 二叉搜索树的最近公共祖先 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p = root.left.right.left;
        TreeNode q = root.left.right.right;
        System.out.println(lowestCommonAncestor(root, p, q).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 这里可以再加深下印象 模拟下
        if (root == null || root.left == null|| root.right == null) {
            return root;
        }
        // 当前节点和p、q进行比较
        if (root.val > p.val && root.val > q.val) {
            root = lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            root = lowestCommonAncestor(root.right, p, q);
        }
        return root;

    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 记录路径
        List<TreeNode> pPath = getPath(root, p);
        List<TreeNode> qPath = getPath(root, q);

        TreeNode result = null;

        for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) == qPath.get(i)) {
                result = pPath.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode temp) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != temp) {
            path.add(node);
            if (node.val < temp.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        path.add(node);
        return path;
    }
}
