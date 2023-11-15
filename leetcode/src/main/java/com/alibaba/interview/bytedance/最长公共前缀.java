package com.alibaba.interview.bytedance;

/**
 * @author quanhangbo
 * @date 2023/11/15 16:40
 */
public class 最长公共前缀 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        String prev = strs[0];
        for (int i = 1; i < strs.length; i ++ ) {
            prev = commons(prev, strs[i]);
        }
        return prev;
    }

    public String commons(String s1, String s2) {
        String res = "";
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                res += s1.charAt(i);
                i ++;
                j ++;
            } else {
                break;
            }
        }
        return res;
    }
}
