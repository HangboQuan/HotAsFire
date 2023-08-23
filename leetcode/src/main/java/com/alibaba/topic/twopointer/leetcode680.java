package com.alibaba.topic.twopointer;

/**
 * @author quanhangbo
 * @date 2023/8/23 20:32
 */
public class leetcode680 {

    public static void main(String[] args) {
        System.out.println(validPalindrome("alibaba"));
    }

    public static boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < s.length() / 2; ) {
            if (s.charAt(i) != s.charAt(j)) {
                return isValid(s, i + 1, j) || isValid(s, i, j - 1);
            }
            i ++;
            j --;
        }
        return true;
    }

    public static boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i ++;
                j --;
            } else {
                return false;
            }
        }
        return true;
    }



}
