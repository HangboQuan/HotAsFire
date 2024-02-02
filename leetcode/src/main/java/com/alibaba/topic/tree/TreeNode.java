package com.alibaba.topic.tree;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/8/18 11:18
 */
public class TreeNode {

    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int val) {
        this.val = val;
    }


    /**
     * 二叉树系列高频面试题
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.right = new TreeNode(9);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(2);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(8);
        // 层级遍历
        levelTreeNode(node);
        // 前序遍历
        preOrderTraverse(node);
        System.out.println();
        // 中序遍历
        inOrderTraverse(node);
        System.out.println();
        inOrderTree(node);
        System.out.println();
        System.out.println(inorderSuccessor(node, node.right));
        // 后序遍历
        afterOrderTraverse(node);
        System.out.println();


        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(3);
        root.left.left.left.left = new TreeNode(2);

        TreeNode root1 = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(5);
        root1.right.right.right.right = new TreeNode(6);
        // 二叉树的最大深度
        System.out.println(maxDepth(node));
        // 二叉树的最小深度
//        System.out.println(minDepth(root));

        System.out.println("======================================");
        System.out.println(minDepth(root1));

    }


    /**
     * 层级遍历
     * @param root
     */
    public static void levelTreeNode(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> re = new ArrayList<>();
                for (int i = 0; i < size; i ++ ) {
                    TreeNode tmp = queue.poll();
                    if (tmp.left != null) {
                        queue.add(tmp.left);
                    }
                    if (tmp.right != null) {
                        queue.add(tmp.right);
                    }
                    re.add(tmp.val);
                }
                ans.add(re);
            }
        }
        for (List<Integer> r : ans) {
            System.out.println(r);
        }
    }

    // 二叉树非递归前序遍历
    public static void preOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            System.out.print(tmp.val + " ");
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
    }

    // 二叉树非递归中序遍历
    public static void inOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root  = root.left;
                } else {
                    TreeNode tmp = stack.pop();
                    System.out.print(tmp.val + " ");
                    root = tmp.right;
                }

            }
        }
    }

    // 二叉树非递归后续遍历
    public static void afterOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode cur = stack1.pop();
            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }

        while (!stack2.empty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }


    /**
     * 思路：分别计算左子树的最大深度和右子树的最大深度 然后再加上根节点(DFS)
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        return Math.max(left, right) + 1;

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> res = new ArrayList<>();
                for (int i = 0; i < size; i ++ ) {
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                    res.add(cur.val);
                }
                ans.add(res);
            }
        }
        return ans.size();
    }

    /**
     * 思路：分别计算左子树的最小深度和右子树的最小深度 然后再加上根节点 需要特殊处理线性树(DFS)
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int left = minDepth(root.left);
//        int right = minDepth(root.right);
//        if (root.left == null || root.right == null) {
//            return left + right + 1;
//        } else {
//            return Math.min(left, right) + 1;
//        }
        if (root == null) {
            return 0;
        }
        int count = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++ ) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return count;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            count ++;
        }
        return count;
    }

    // 最近公共祖先
    /**
     * 分类讨论：
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    public static void inOrderTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode tmp = stack.pop();
            if (prev == p) {
                return tmp;
            }
            prev = tmp;
            root = tmp.right;
        }
        return null;
    }
    // 给定一个二叉树，返回按照中序遍历结果的前一个节点，空间复杂度O(1) ?
}
