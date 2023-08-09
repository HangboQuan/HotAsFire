package com.alibaba.weekly.w357;

/**
 * @author quanhangbo
 * @date 2023/8/8 21:57
 */
public class leetcode2810 {

    public static void main(String[] args) {
        System.out.println(finalString("poiinter"));
    }

    public static String finalString(String s) {
        String newString = "";
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == 'i') {
                newString = reverseString(newString);
            } else {
                newString += s.charAt(i);
            }
        }
        return newString;
    }

    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
