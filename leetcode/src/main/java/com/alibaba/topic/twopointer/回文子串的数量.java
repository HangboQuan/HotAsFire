package com.alibaba.topic.twopointer;

/**
 * @author quanhangbo
 * @date 2024/1/25 14:37
 */
public class 回文子串的数量 {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abaaba"));
        System.out.println(countSubstrings("alibaba"));
    }

    public static int countSubstrings(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i ++ ) {
            res += isPalind(s, i, i);
            res += isPalind(s, i, i + 1);
        }
        return res;
    }

    public static int isPalind(String s, int i, int j) {
        int ans = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
            ans ++;
            i --;
            j ++;
        }
        return ans;
    }
}
