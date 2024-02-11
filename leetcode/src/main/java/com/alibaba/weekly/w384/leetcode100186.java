package com.alibaba.weekly.w384;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2024/2/11 17:35
 */
public class leetcode100186 {

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int res = 0;
        for (int i = 0; i < nums.length - pattern.length; i ++ ) {
            int j = i;
            int[] ans = Arrays.copyOfRange(nums, i, i + pattern.length + 1);
            if (dealWith(ans, pattern)) {
                res ++;
            }
        }
        return res;
    }


    public boolean dealWith(int[] res, int[] pattern) {
        boolean tag = true;
        for (int i = 1; i < res.length; i ++ ) {
            if ((res[i - 1] < res[i] && pattern[i - 1] == 1) || (res[i - 1] == res[i] && pattern[i - 1] == 0) ||
                    (res[i - 1] > res[i] && pattern[i - 1] == -1)) {

            } else {
                tag = false;
                break;
            }
        }
        return tag;
    }
}
