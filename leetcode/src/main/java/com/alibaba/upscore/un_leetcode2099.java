package com.alibaba.upscore;

import java.util.PriorityQueue;

/**
 * @author quanhangbo
 * @date 2024/2/7 16:09
 */
public class un_leetcode2099 {

    public static void main(String[] args) {

    }


    public int[] maxSubsequence(int[] nums, int k) {
        //
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k, (o1, o2) -> {
            return o2[1] - o1[1];
        });
        for (int i = 0; i < nums.length; i ++ ) {
            pq.add(new int[]{i, nums[i]});
        }

        PriorityQueue<int[]> pr = new PriorityQueue<int[]>(k, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        int index = k;
        while (index > 0) {
            pr.add(pq.poll());
            index --;
        }

        int[] res = new int[k];
        int count = 0;
        while (!pr.isEmpty()) {
            res[count ++] = pr.poll()[1];
        }
        return res;
    }


    public int garbageCollection(String[] garbage, int[] travel) {
        int total = 0;
        int mLast = 0;
        int pLast = 0;
        int gLast = 0;
        for (int i = 0; i < garbage.length; i ++ ) {
            char[] ch = garbage[i].toCharArray();
            for (char c : ch) {
                if (c == 'M') {
                    mLast = i;
                }
                if (c == 'P') {
                    pLast = i;
                }
                if (c == 'G') {
                    gLast = i;
                }
            }
        }
        if (mLast > 0) {
            for (int i = 0; i < mLast; i ++ ) {
                total += travel[i];
            }
        }
        if (pLast > 0) {
            for (int i = 0; i < pLast; i ++ ) {
                total += travel[i];
            }
        }
        if (gLast > 0) {
            for (int i = 0; i < gLast; i ++ ) {
                total += travel[i];
            }
        }
        return total;
    }
}
