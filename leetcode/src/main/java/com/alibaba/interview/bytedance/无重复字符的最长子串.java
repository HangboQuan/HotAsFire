package com.alibaba.interview.bytedance;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2023/11/15 9:56
 */
public class 无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        // "abcabcbb" 经典滑动窗口
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int max = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                max = Math.max(max, j - i + 1);
                set.add(s.charAt(j));
                j ++;
            } else {
                set.remove(s.charAt(i));
                i ++;
            }
        }
        return max;
    }
}
