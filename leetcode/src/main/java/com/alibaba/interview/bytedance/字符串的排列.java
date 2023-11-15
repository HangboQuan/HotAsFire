package com.alibaba.interview.bytedance;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2023/11/14 19:03
 */
public class 字符串的排列 {

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] count1 = new int[26];
        for (int i = 0; i < s1.length(); i ++ ) {
            count1[s1.charAt(i) -'a'] ++;
        }
        for (int i = 0; i <= s2.length() - s1.length(); i ++ ) {
            String s = s2.substring(i, i + s1.length());
            int[] count2 = new int[26];
            for (int j = 0; j < s.length(); j ++ ) {
                count2[s.charAt(j) - 'a'] ++;
            }
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }
        return false;
    }
}
