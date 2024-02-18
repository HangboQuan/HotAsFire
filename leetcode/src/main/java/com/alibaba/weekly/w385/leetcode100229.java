package com.alibaba.weekly.w385;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/18 15:16
 */
public class leetcode100229 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000}));
    }


    public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<String> set = new HashSet<>();
        for (int v : arr1) {
            String s = String.valueOf(v);
            for (int i = 1; i <= s.length(); i ++ ) {
                set.add(s.substring(0, i));
            }
        }

        int max = 0;
        for (int v : arr2) {
            String str = String.valueOf(v);
            for (int i = 1; i <= str.length(); i ++ ) {
                if (!set.contains(str.substring(0, i))) {
                    break;
                }
                max = Math.max(max, i);
            }
        }
        return max;
    }
}
