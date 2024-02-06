package com.alibaba.upscore;

import java.util.Arrays;
import java.util.Random;

/**
 * @author quanhangbo
 * @date 2024/2/6 17:15
 */
public class un_leetcode942 {

    public static void main(String[] args) {

    }

    // 贪心算法: I是剩余数字中最小的元素 D是剩余数字中最大的元素
    public int[] diStringMatch(String s) {
        int[] res = new int[s.length() + 1];
        int left = 0, right = s.length();
        for (int i = 0; i < s.length(); i ++ ) {
            if (s.charAt(i) == 'I') {
                // 选取最小的值 即left
                res[i] = left ++;
            } else {
                res[i] = right --;
            }
        }
        res[res.length - 1] = left;
        return res;
    }
}
