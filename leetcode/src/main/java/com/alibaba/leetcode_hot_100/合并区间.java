package com.alibaba.leetcode_hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author quanhangbo
 * @date 2025-03-05 17:35
 */
public class 合并区间 {

    public static void main(String[] args) {
        int[][] res = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] ans = merge(res);
        for (int[] an : ans) {
            System.out.println(an[0] + " " + an[1]);
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        ArrayList<int[]> ans = new ArrayList<>();
        for (int i = 0; i < intervals.length; i ++) {
            int left = intervals[i][0];
            int right = intervals[i][1];

            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < left) {
                ans.add(new int[]{left, right});
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], right);
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }

}
