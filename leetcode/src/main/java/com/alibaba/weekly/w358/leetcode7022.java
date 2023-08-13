package com.alibaba.weekly.w358;
import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/8/13 16:26
 */
public class leetcode7022 {

    public static void main(String[] args) {
        System.out.println(minAbsoluteDifferent(Arrays.asList(5, 3, 2, 10, 15), 1));
    }

    public static int minAbsoluteDifferent(List<Integer> nums, int x) {
        // 双指针并不能解决
//        int min = Integer.MAX_VALUE;
//        int start = 0;
//        int end = nums.size() - 1;
//
//        while (start <= end) {
//            if (Math.abs(nums.get(start) - nums.get(end)) < min && Math.abs(start - end) >= x) {
//                min = Math.abs(nums.get(start) - nums.get(end));
//            }
//
//            if (Math.abs(start - end - 1) < x) {
//                return min;
//            }
//            int next1 = Math.abs(nums.get(start) - nums.get(end - 1));
//            int next2 = Math.abs(nums.get(start + 1) - nums.get(end));
//
//            if (next1 < next2) {
//                end--;
//            } else {
//                start++;
//            }
//        }
//        return min;

        TreeSet<Integer> set = new TreeSet<>();
        int n = nums.size();
        int re = Integer.MAX_VALUE;
        for (int i = x; i < n; i ++ ) {
            int cur = nums.get(i);
            set.add(nums.get(i - x));
            // returns the least element in this set greater than or equal to the given element, or null if there is no such element
            // 返回该集合中大于或等于给定元素的最小元素 如果没有，返回null
            Integer a = set.ceiling(cur);

            if (a != null) {
                re = Math.min(re, Math.abs(a - cur));
            }
            // returns the greatest element in this set less than or equal to the given element, or null if there is no such element
            // 返回该集合中小于或等于给定元素的最大元素 如果没有，返回null
            a = set.floor(cur);
            if (a != null) {
                re = Math.min(re, Math.abs(a - cur));
            }

        }
        return re;
    }
}