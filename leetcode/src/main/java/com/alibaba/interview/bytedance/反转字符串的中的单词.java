package com.alibaba.interview.bytedance;

/**
 * @author quanhangbo
 * @date 2023/11/14 19:07
 */
public class 反转字符串的中的单词 {

    // 空间复杂度O(1)
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        int i = s.length() - 1, j = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i --;
            }
            sb.append(s.substring(i + 1, j + 1)).append(' ');
            while (i >= 0 && s.charAt(i) == ' ') {
                i --;
            }
            j = i;
        }
        return sb.toString().trim();
    }

    // 空间复杂度O(n)
    public String reverseWords1(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        String[] str = s.split(" +");
        for (int i = str.length - 1; i >= 0; i -- ) {
            sb.append(str[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
