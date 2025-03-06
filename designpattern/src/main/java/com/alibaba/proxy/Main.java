package com.alibaba.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-03-03 17:29
 */
public class Main {

    static List<List<Integer>> t = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
//        root.right = new TreeNode(1);
//        root.left.left = new TreeNode(4);
        int n = 12;
        List<Integer> ans = new ArrayList<>();
        dfs(root, n, ans);
        for (List<Integer> r : t) {
            System.out.println(r);
        }
    }

    public static void dfs(TreeNode root, int target, List<Integer> res) {
        if (root == null) {
            return ;
        }
        res.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            t.add(new ArrayList<>(res));
            return ;
        }

        dfs(root.left, target - root.val, res);
        dfs(root.right, target - root.val, res);
//        res.remove(res.size() - 1);
    }
}


class TreeNode {
     TreeNode left;
     TreeNode right;
     int val;

    public TreeNode(int val) {
        this.val = val;
    }
}
