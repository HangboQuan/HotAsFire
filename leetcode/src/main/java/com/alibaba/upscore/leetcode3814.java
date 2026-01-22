package com.alibaba.upscore;

import java.util.ArrayDeque;
import java.util.Arrays;

public class leetcode3814 {

    public static void main(String[] args) {
        int[] cost = new int[]{4, 8, 5, 3};
        int[] cap = new int[]{1, 5, 2, 7};

        Integer[] idx = new Integer[cost.length];
        for (int i = 0; i < cost.length; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> (cost[i] - cost[j]));

    }

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        // 单调栈
        Integer[] idx = new Integer[costs.length];
        for (int i = 0; i < costs.length; i++) {
            idx[i] = i;
        }
        // 排完序 costs[idx[i]]和capacity[idx[i]]是一一对应的，而且costs[idx[i]]是从小到大排序的
        Arrays.sort(idx, (i, j) -> costs[i] - costs[j]);

        // 双端队列，可以做栈也可以做队列
        ArrayDeque<int[]> arrayDeque = new ArrayDeque<int[]>();
        // 哨兵
        arrayDeque.push(new int[]{0, 0});
        int ans = 0;
        for (int k = 0; k < costs.length && costs[idx[k]] < budget; k++) {
            int i = idx[k];
            while (!arrayDeque.isEmpty() && costs[i] + arrayDeque.peek()[0] >= budget) {
                arrayDeque.pop();
            }
            ans = Math.max(ans, capacity[i] + (arrayDeque.isEmpty() ? 0 : arrayDeque.peek()[1]));
            if (!arrayDeque.isEmpty() && capacity[i] > arrayDeque.peek()[1]) {
                arrayDeque.push(new int[]{costs[i], capacity[i]} );
            }
        }
        return ans;

    }
}
