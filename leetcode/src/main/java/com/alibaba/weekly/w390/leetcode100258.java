package com.alibaba.weekly.w390;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author quanhangbo
 * @date 2024-03-24 17:10
 */
public class leetcode100258 {

    /**
     * 运行超时
     * @param nums
     * @param freq
     * @return
     */

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


    /**
     * 可以通过
     * @param nums
     * @param freq
     * @return
     */
    public long[] mostFrequentIDs1(int[] nums, int[] freq) {
        TreeMap<Long, HashSet<Integer>> map = new TreeMap<>();
        long[] ans = new long[nums.length];
        long[] arr = new long[100005];

        for (int i = 0; i < nums.length; i ++ ) {
            long oldTime = arr[nums[i]];
            arr[nums[i]] += freq[i];

            if (map.containsKey(oldTime)) {
                map.get(oldTime).remove(nums[i]);
                if (map.get(oldTime).size() == 0) {
                    map.remove(oldTime);
                }
            }


            HashSet<Integer> newVal = map.containsKey(arr[nums[i]]) ? map.get(arr[nums[i]]) : new HashSet<>();
            newVal.add(nums[i]);
            map.put(arr[nums[i]], newVal);
            ans[i] = map.lastKey();
        }

        return ans;
    }
}
