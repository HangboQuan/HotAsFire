package com.alibaba.leetcode_hot_100;

import java.util.HashSet;

/**
 * @author quanhangbo
 * @date 2023/10/16 11:39
 */
public class 无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int max = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                j ++;
                max = Math.max(max, j - i);
                set.add(s.charAt(j));
            } else {
                i ++;
            }
        }
        return max;
    }


}
