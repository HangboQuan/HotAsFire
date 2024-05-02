package com.alibaba.weekly.W392;

/**
 * @author quanhangbo
 * @date 2024-04-27 22:43
 */
public class leetcode3121 {

    public static void main(String[] args) {

    }

    public int numberOfSpecialChars(String word) {
        int result = 0;
        boolean[] ans = new boolean[26];
        for (int i = 0; i < ans.length; i++) {
            if (word.contains(String.valueOf((char)('a' + i))) && word.contains(String.valueOf((char)('a' - 32 + i)))) {
                ans[i] = true;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i]) {
                int lastSmall = word.lastIndexOf('a' + i);
                int firstLarge = word.indexOf('a' - 32 + i);
                if (lastSmall < firstLarge) {
                    result++;
                }
            }
        }
        return result;
    }

}
