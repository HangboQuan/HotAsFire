package com.alibaba.weekly.w390;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024-03-24 17:12
 */
public class leetcode100245 {

    public int maximumLengthSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i ++) {
            for (int j = i + 1; j <= s.length(); j ++) {
                String ans = s.substring(i, j);
                max = Math.max(max, validResult(ans));
            }
        }
        return max;
    }

    public int validResult(String s) {
        int[] cnt = new int[26];
        boolean flag = false;
        int sum = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i ++) {
            cnt[s.charAt(i) - 'a'] ++;
            set.add(s.charAt(i));
        }
        for (int v : cnt) {
            sum += v;
        }
        int num = 0;
        for (int i = 0; i < cnt.length; i ++) {
            if (cnt[i] <= 2) {
                num ++;
            }
        }
        if (num == cnt.length) flag = true;
        if (flag) return s.length();
        return 0;
    }
}
