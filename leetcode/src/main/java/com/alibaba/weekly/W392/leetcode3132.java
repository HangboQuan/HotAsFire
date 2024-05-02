package com.alibaba.weekly.W392;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2024-05-01 20:26
 */
public class leetcode3132 {

    public static void main(String[] args) {

    }

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 2; i >= 0; i--) {
            int diff = nums2[0] - nums1[i];
            int j = 0;

            for (int k = i; k < nums1.length; k++) {
                if (nums1[k] + diff == nums2[j] && ++j == nums2.length) {
                    return diff;
                }
            }
        }
        return nums2[0] - nums1[0];
    }

}
