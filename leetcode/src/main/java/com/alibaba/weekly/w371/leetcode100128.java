package com.alibaba.weekly.w371;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/11/12 10:31
 */
public class leetcode100128 {

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        List<String> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
//        这块几乎浪费了半个多小时
//        Collections.sort(access_times, new Comparator<List<String>>() {
//            @Override
//            public int compare(List<String> o1, List<String> o2) {
//                return o1.get(0).compareTo(o2.get(0));
//            }
//        });
//        for (List<String> s : access_times) {
//            if (!map.containsKey(s.get(0))) {
//                times = new ArrayList<>();
//                times.add(s.get(1));
//                map.put(s.get(0), times);
//            } else {
//                times.add(s.get(1));
//                map.put(s.get(0), times);
//            }
//        }

        for (List<String> s : access_times) {
            if (!map.containsKey(s.get(0))) {
                map.put(s.get(0), new ArrayList<>());
            }
            map.get(s.get(0)).add(s.get(1));
        }

        for (Map.Entry<String, List<String>> m : map.entrySet()) {
            if (m.getValue().size() >= 3) {
                String[] ans = new String[m.getValue().size()];
                int[] value = new int[ans.length];
                for (int i = 0; i < m.getValue().size(); i ++ ) {
                    value[i] = parser(m.getValue().get(i));
                }
                Arrays.sort(value);
                for (int i = 0; i < value.length - 2; i ++ ) {
                    if (value[i + 2] - value[i] <= 59) {
                        res.add(m.getKey());
                        break;
                    }
                }
            }
        }
        return res;
    }

    public int parser(String s) {
        int front = Integer.parseInt(s.substring(0, 2));
        int end = Integer.parseInt(s.substring(2));
        return front * 60 + end;
    }


    public int maximumStrongPairXor(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i ++ ) {
            for (int j = i + 1; j < nums.length; j ++ ) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])) {
                    max = Math.max(max, (nums[i] ^ nums[j]));
                }
            }
        }
        return max;
    }
}
