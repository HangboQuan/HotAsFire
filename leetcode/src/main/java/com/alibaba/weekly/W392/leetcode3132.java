package com.alibaba.weekly.W392;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;

/**
 * @author quanhangbo
 * @date 2024-05-01 20:26
 */
public class leetcode3132 {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("alibaba", 0);
        map.put("bytedance", 1);
        map.put("tencent", 2);
        map.put("baidu", 3);
        map.put("pdd", 4);
        map.put("meituan", 5);
        map.put("xiaomi", 6);
        map.put("ant", 7);
        map.put("jd", 8);
        map.put("anlushan", 9);
        map.put("quanhangbo", 10);
        map.put("shisiming", 11);
        map.put("lilinfu", 12);
        map.put("yangguohzong", 13);
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
