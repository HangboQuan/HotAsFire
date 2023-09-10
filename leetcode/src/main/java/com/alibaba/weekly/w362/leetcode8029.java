package com.alibaba.weekly.w362;


import javafx.util.Pair;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/9/10 15:00
 */
public class leetcode8029 {

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(3, 6));
        nums.add(Arrays.asList(1, 5));
        nums.add(Arrays.asList(4, 7));

        nums.add(Arrays.asList(8, 10));
//        System.out.println(numberOfPoints(nums));
        System.out.println(numberOfPoints0(nums));
    }

    public static int numberOfPoints(List<List<Integer>> nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.size(); i ++ ) {
            for (int j = nums.get(i).get(0); j < nums.get(i).get(1); j ++) {
                set.add(j);
            }
        }
        return set.size();
    }

    // 合并区间
    public static int numberOfPoints0(List<List<Integer>> nums) {
        Collections.sort(nums, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        int[][] res = nums.stream().map(num -> num.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        int st = -1, ed = -1;
        List<Pair<Integer, Integer>> ans = new ArrayList<>();

        for (int i = 0; i < res.length; i ++ ) {
            if (res[i][0] > ed) {
                if (st != -1) {
                    ans.add(new Pair<>(st, ed));
                }
                st = res[i][0];
                ed = res[i][1];
            } else {
                ed = Math.max(ed, res[i][1]);
            }
        }
        if (st != -1) {
            ans.add(new Pair<>(st, ed));
        }
        int sum = 0;
        for (Pair<Integer, Integer> tmp : ans) {
            sum += tmp.getValue() - tmp.getKey() + 1;
        }
        return sum;
    }
}
