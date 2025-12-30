package com.alibaba.upscore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode624 {
    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<List<Integer>>(){{
            add(Arrays.asList(1, 2, 3));
            add(Arrays.asList(4, 5));
        }};

        List<List<Integer>> arr1 = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(1));
            add(Arrays.asList(1));
        }};

        List<List<Integer>> arr2 = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(1, 5));
            add(Arrays.asList(3, 4));
        }};

        List<List<Integer>> arr3 = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(-3, -3));
            add(Arrays.asList(-3, -2));
        }};
        System.out.println(maxDistance(arr));
        System.out.println(maxDistance2(arr));
        System.out.println(maxDistance2(arr1));
        System.out.println(maxDistance2(arr2));
        System.out.println(maxDistance2(arr3));
    }

    /**
     * 时间复杂度n^2
     * @param arrays
     * @return
     */
    public static int maxDistance(List<List<Integer>> arrays) {
        // res下存储在每个元素的最小值和最大值
        int[][] res = new int[arrays.size()][2];
        int index = 0;
        for (List<Integer> ans : arrays) {
            // res数组分别存储的是 最小值和最大值
            res[index][0] = ans.get(0);
            res[index][1] = ans.get((ans.size() - 1));
            index++;
        }
        int diff = 0;
        for (int i = 0; i < res.length; i++) {
            int min = res[i][0];
            int max = min;
            for (int j = 0; j < res.length; j++) {
                if (i != j) {
                    max = res[j][1];
                }
            }
            diff = Math.max(diff, max - min);
        }
        return diff;
    }

    /**
     * 如何能够优化为n
     * @param arrays
     * @return
     */
    public static int maxDistance2(List<List<Integer>> arrays) {
        // res下存储在每个元素的最小值和最大值
        int[][] res = new int[arrays.size()][2];
        int index = 0;

        // 存储所有的最大值
        List<Integer> tmp = new ArrayList<>();

        // 最大值
        int currMax = Integer.MIN_VALUE;
        // 次大值
        int secondMax = currMax;
        for (List<Integer> ans : arrays) {
            // res数组分别存储的是 最小值和最大值
            res[index][0] = ans.get(0);
            res[index][1] = ans.get((ans.size() - 1));
            tmp.add(res[index][1]);
            if (res[index][1] > currMax) {
                secondMax = currMax;
                currMax = res[index][1];
            } else if (res[index][1] > secondMax) {
                secondMax = res[index][1];
            }
            index++;
        }

        int diff = 0;
        for (int i = 0; i < res.length; i++) {
            int min = res[i][0];
            int max = currMax == res[i][1] ? secondMax : currMax;
            diff = Math.max(diff, max - min);
        }
        return diff;
    }
}
