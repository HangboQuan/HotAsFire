package com.alibaba.topic.simulate;


/**
 * @author quanhangbo
 * @date 2023/8/23 19:52
 */
public class leetcode680 {

    /**
     * 该题模拟无法通过，数据范围为1<n<10^5，下面方法的时间复杂度为 n * n / 2 => n^2
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }

    public static boolean validPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i ++ ) {
            if (palindrome(sb.deleteCharAt(i).toString())) {
                return true;
            }
            sb = new StringBuilder(s);
        }
        return false;
    }

    public static boolean palindrome(String s) {
        int len = s.length() / 2;
        int i = 0;
        while (i < len) {
            if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
                i ++;
            } else {
                return false;
            }
        }
        return true;
    }

}
