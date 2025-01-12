package com.alibaba.upscore;

import com.alibaba.topic.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024/2/7 17:50
 */
public class leetcode2559 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(findBottomLeftValue(root));
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] target = new int[words.length];
        for (int i = 0; i < words.length; i ++ ) {
            String str = words[i];
            if (isTarget(str)) {
                target[i] = 1;
            }
        }
        int[] pre = new int[target.length + 1];
        for (int i = 1; i < pre.length; i ++ ) {
            pre[i] = pre[i - 1] + target[i - 1];
            System.out.println(pre[i]);
        }

        int[] res = new int[queries.length];
        int index = 0;
        for (int i = 0; i < queries.length; i ++ ) {
            int left = queries[i][0];
            int right = queries[i][1];
            res[index ++] = pre[right + 1] - pre[left];
        }
        return res;
    }

    public boolean isTarget(String str) {
        int i = 0;
        int j = str.length() - 1;
        return (str.charAt(i) == 'a' || str.charAt(i) == 'o' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'u') &&
                (str.charAt(j) == 'a' || str.charAt(j) == 'o' || str.charAt(j) == 'e' || str.charAt(j) == 'i' || str.charAt(j) == 'u');
    }



    public static int findBottomLeftValue(TreeNode root) {
        int high = treeHigh(root);
        return findValue(root, 1, high);
    }

    public static int findValue(TreeNode root, int start, int high) {
        if (root == null) {
            return -1;
        }
        if (root.left != null && start == high - 1) {
            System.out.println(root.left.val);
            return root.left.val;
        }
        findValue(root.left, start + 1, high);
        findValue(root.right, start + 1, high);
        System.out.println(root.val);
        return -1;

    }
        public static int treeHigh(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(treeHigh(root.left), treeHigh(root.right)) + 1;
        }
}
