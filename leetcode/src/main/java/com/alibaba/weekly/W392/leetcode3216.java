package com.alibaba.weekly.W392;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2024-07-19 8:48
 */
public class leetcode3216 {

    public static void main(String[] args) {

    }


    public String getSmallestString(String s) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length - 1; i++) {
            if (shouldSwap(ch[i], ch[i + 1])) {
                swap(ch, i, i + 1);
                break;
            }
        }
        return new String(ch);
    }

    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    public boolean shouldSwap(char a, char b) {
        return ((a % 2  == 0 && b % 2 == 0) || (a % 2 == 1 && b % 2 == 1)) && a > b;
    }
}
