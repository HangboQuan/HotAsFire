package com.alibaba.weekly.w390;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2024-03-24 17:10
 */
public class leetcode100258 {

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        long[] res = new long[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < freq.length; i ++ ) {
            int max = 0;
            map.put(nums[i], map.getOrDefault(nums[i], 0) + freq[i]);

            for (Map.Entry<Integer, Integer> m : map.entrySet()) {
                if (m.getValue() > max) {
                    max = m.getValue();
                }
            }
            res[i] = max;
        }
        return res;
    }
}
