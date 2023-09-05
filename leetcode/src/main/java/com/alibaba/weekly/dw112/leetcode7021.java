package com.alibaba.weekly.dw112;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/9/2 22:30
 */
public class leetcode7021 {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 2);
        System.out.println(maxSum(nums, 2, 2));
    }

    public static boolean canBeEqual(String s1, String s2) {
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < s2.length() - 2; i ++ ) {
            if ((s1.charAt(i) == s2.charAt(i + 2) || s1.charAt(i) == s2.charAt(i)) &&
                    (s1.charAt(i + 2) == s2.charAt(i) || s1.charAt(i + 2) == s2.charAt(i + 2))) {

            } else {
                return false;
            }
        }
        return true;
    }

    // 前缀和
    public static long maxSum(List<Integer> nums, int m, int k) {
        long[] preSum = new long[nums.size() + 1];
        long max = 0;
        preSum[0] = 0;
        for (int i = 1; i <= nums.size(); i ++ ) {
            preSum[i] = preSum[i - 1] + nums.get(i - 1);

        }

        for (int i = 0; i < preSum.length; i ++ ) {
            System.out.println(preSum[i]);
        }

        for (int i = k; i < preSum.length; i ++ ) {
            long sum = preSum[i] - preSum[i - k];
            Set<Integer> sets = new HashSet<>();
//            System.out.println(sum);
            int j = i;
            System.out.println(j + "-");
            int count = 0;
            while (j != 0) {
                if (sets.size() >= m) break;
                sets.add(nums.get(-- j));
                count ++;
                if (count == k) break;

            }
            System.out.println(sets);
            if (sets.size() >= m && sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
