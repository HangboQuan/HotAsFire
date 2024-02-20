package com.alibaba.leetcode_top_100;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/20 20:44
 */
public class leetcode3无重复字符的最长子串 {



    // abcdedafg
    // 5
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(i))) {
                max = Math.max(max, j - i + 1);
                set.add(s.charAt(i));
                i ++;
            } else {
                set.remove(s.charAt(j));
                j ++;
            }
        }
        return max;

    }


    // abcdedafg 好未来3面 可惜我只写出了O(n^3)的时间复杂度
    // 5
    public static void main(String[] args) {
        System.out.println(maxNoRepeatString("abcdedafg"));
        System.out.println(maxNoRepeatString("aaaa"));
    }

    public static int maxNoRepeatString(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i ++ ) {
            for (int j = i + 1; j <= s.length(); j ++ ) {
                String str = s.substring(i, j);
                if (noRepeat(str)) {
                    max = Math.max(max, str.length());
                }
            }
        }
        return max;
    }

    public static boolean noRepeat(String s) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i ++ ) {
            set.add(s.charAt(i));
        }
        return set.size() == s.length();
    }
}
