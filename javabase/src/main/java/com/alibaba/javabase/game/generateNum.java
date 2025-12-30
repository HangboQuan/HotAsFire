package com.alibaba.javabase.game;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

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

    static int len = 9;
    public static void dfs(int[][] arr) {
        for (int i = 0;i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 1. 随机生成一个1-9的数字
                int x = getRandomNum();
                // 2. 判断x是否能够在棋盘上存放
                boolean ding = dingThis(x);

                // 3. 可以存放
                if (ding) {
                    arr[i][j] = x;
                } else {
                    // 不能存放？如何进行回溯？
                }
            }
        }
    }

    public static int getRandomNum() {
        // 随机生成一个1-9的数字
        return 0;
    }

    public static boolean dingThis(int[][] arr, int i, int j, int x) {
        for (int m = 0; m < arr[0].length; m++) {
            // 每一列 出现重复值
            if (arr[i][m] == x) {
                return false;
            }
        }

        for (int m = 0; m < arr.length; m++) {
            // 每一行 出现重复值
            if (arr[m][j] == x) {
                return false;
            }
        }

        Pair<Integer, Integer> pair = matrixThreeIndex(i, j);
        int indexI = pair.getLeft();
        int indexJ = pair.getRight();

//        if (indexI == -1 && indexJ == -1) {
//            return false;
//        }

        for (int n = indexI; n < indexI + 3; n++) {
            for (int o = indexJ; o < indexJ + 3; o++) {
                if (arr[n][o] == x) {
                    return false;
                }
            }
        }

        return true;
    }

    public static Pair<Integer, Integer> matrixThreeIndex(int i, int j) {
        int indexI = 0;
        int indexj = 0;
        if (i >= 0 && i <= 2) {
            indexI = 0;
        } else if (i >= 3 && i <= 5) {
            indexI = 3;
        } else if (i >= 6 && i <= 8) {
            indexI = 6;
        }

        if (j >= 0 && j <= 2) {
            indexj = 0;
        } else if (j >= 3 && j <= 5) {
            indexj = 3;
        } else if (j >= 6 && j <= 8) {
            indexj = 6;
        }

//        if (Math.abs(indexI - indexj) > 2) {
//            return Pair.of(-1, -1);
//        }
        return Pair.of(indexI, indexj);
    }

    public static boolean dingThis(int x) {
        return true;
    }


    // 每次只处理一个格子
    public static void dfs2(int[][] arr, int i, int j) {
        if (i == len && j == len) {
            for (int[] a : arr) {
                System.out.println(Arrays.asList(a));
            }
            return ;
        }

        // 1. 随机的选取一个1-9数字
        int x = getRandomNum();
        // 2. 判断是否能填在当下
        boolean ding = dingThis(x);
        if (ding) {
            // 可以填写，优先给右边放，如果右边满了，再给下一行的最左边放
            if (j == len) {
                dfs2(arr, i + 1, 0);
                arr[i][j] = 0;
            } else {
                dfs2(arr, i, j + 1);
                arr[i][j] = 0;
            }
        }

    }





}
