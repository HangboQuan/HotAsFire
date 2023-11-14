package com.alibaba.score;

/**
 * @author quanhangbo
 * @date 2023/11/14 15:23
 */
public class 最大化数组末位元素的最少操作次数 {

    public int minOperations(int[] nums1, int[] nums2) {
        // 分两种情况讨论，交换n1[end]和n2[end], 不交换n1[end]和n2[end]
        // 对每种情况枚举[0, n-2],如果n1[i]>n1[end]或者n2[i]>n2[end]必须执行交换操作
        // 如果操作后仍然n1[i]>n1[end]或者n2[i]>n2[end]则说明无法满足要求
        int end = nums1.length - 1;
        int ans = Math.min(f(nums1[end], nums2[end], nums1, nums2),
                1 + f(nums2[end], nums1[end], nums1, nums2));
        return ans > nums1.length ? -1 : ans;
    }

    public int f(int last1, int last2, int[] n1, int[] n2) {
        int res = 0;
        for (int i = 0; i < n1.length - 1; i ++ ) {
            if (n1[i] > last1 || n2[i] > last2) {
                if (n2[i] > last1 || n1[i] > last2) {
                    return n1.length + 1;
                }
                res ++;
            }
        }
        return res;
    }
}
