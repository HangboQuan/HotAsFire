package com.alibaba.javabase.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/3/15 18:17
 */
public class generateNum {

    static int[][] ans = new int[9][9]; // 初始化一个9 * 9的矩阵
    public static void main(String[] args) {

//        boolean[] col = new boolean[9];
        System.out.println(Math.sqrt(4));


    }

//    public static boolean fillRow(int row) { // 第几行
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < 9; i ++ ) {
//            set.add(ans[row][i]);
//        }
//        return set.size() == 9;
//    }
//
//    public static boolean fillCol(int col) { // 第几列
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < 9; i ++ ) {
//            set.add(ans[i][col]);
//        }
//        return set.size() == 9;
//    }
//
//    public static boolean fillMatrix(int row, int col) {
//        return false;
//    }

    public int maximumLengthSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i ++) {
            for (int j = i + 1; j < s.length(); j ++) {
                String ans = s.substring(i, j);
                max = Math.max(max, validResult(ans));
            }
        }
        return max;
    }

    public int validResult(String s) {
        int[] cnt = new int[26];
        boolean flag = false;
        int sum = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i ++) {
            cnt[s.charAt(i) - 'a'] ++;
            set.add(s.charAt(i));
        }
        for (int v : cnt) {
            sum += v;
        }

        for (int i = 0; i < cnt.length; i ++) {
            if (sum == set.size() + 1 && cnt[i] == 2 && !flag) {
                flag = true;
                return s.length();
            }
        }
        return 0;
    }


    // 判断一个数是否为合数
    public boolean isComposite(int n) {
        if (n <= 1) return false;
        if (n == 2) return false;
        if (n % 2 == 0) return true;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }

    // 找到大于给定数 k 的下一个合数
    public int nextComposite(int k) {
        int n = k + 1;
        while (!isComposite(n)) {
            n++;
        }
        return n;
    }

    public int minOperations(int k) {
        int a = (int)Math.sqrt(k) + 1;
        int b = k / a;
        if (k % a == 0) {
            return a + b - 1;
        }
        return a + b - 2;
    }




}
