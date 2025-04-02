package com.alibaba.strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author quanhangbo
 * @date 2025-03-20 21:19
 */
public class Main {

    // 给一个乱序数组，找出其中第 K 大的数字
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        levelOrder(root);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < queue.size(); i ++) {
                ans.add(t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            res.add(ans);
        }
        return res;
    }

    public static int getKthNum(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    public static void quickSort(int[] nums, int left, int right) {
        int i = left;
        int j = right;
        int x = nums[i];

        while (i < j) {
            while (i < j && nums[j] > x) {
                j --;
            }
            if (i < j) {
                nums[i ++] = nums[j];
            }

            while (i < j && nums[i] < x) {
                i ++;
            }
            if (i < j) {
                nums[j --] = nums[i];
            }
        }
        nums[i] = x;
        quickSort(nums, left, i);
        quickSort(nums, i + 1, right);


    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
