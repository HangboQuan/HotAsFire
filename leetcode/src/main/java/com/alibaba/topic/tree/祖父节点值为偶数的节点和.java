package com.alibaba.topic.tree;

/**
 * @author quanhangbo
 * @date 2024/3/21 14:44
 */
public class 祖父节点值为偶数的节点和 {
    int sum = 0;
    public int sumEvenGrandparent(TreeNode root) {

        if (root == null) {
            return 0;
        }

        if (root.val % 2 == 0) {
            if (root.left != null) {
                if (root.left.left != null) {
                    sum += root.left.left.val;
                }
                if (root.left.right != null) {
                    sum += root.left.right.val;
                }
            }

            if (root.right != null) {
                if (root.right.left != null) {
                    sum += root.right.left.val;
                }
                if (root.right.right != null) {
                    sum += root.right.right.val;
                }
            }
        }
        sumEvenGrandparent(root.left);
        sumEvenGrandparent(root.right);
        return sum;
    }
}
