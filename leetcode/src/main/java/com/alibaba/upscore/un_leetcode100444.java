package com.alibaba.upscore;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2024-12-01 20:51
 */
public class un_leetcode100444 {

    public static int getLargestOutlier(int[] nums) {
        int totalSum = 0;
        HashMap<Integer, Integer> cnt = new HashMap<>();

        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
            totalSum += num;
        }

        // 设异常值为X，另一个值为Y = (total - x) / 2;
        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            cnt.merge(num, -1, Integer::sum);
            if ((totalSum - num) %  2 == 0 && cnt.getOrDefault((totalSum - num) /  2, 0) > 0) {
                ans = Math.max(ans, num);
            }
            cnt.merge(num, 1, Integer::sum);
        }
        return ans;
    }

    public int smallestNumber(int n) {
        int i = 1;
        int res = 0;
        while (true) {
            res = 1 << (i - 1);
            if (res > n) {
                return res - 1;
            }
            i++;
        }
    }
}
