package com.alibaba.upscore;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2024/2/6 16:08
 */
public class leetcode1652 {

    public static void main(String[] args) {

    }

    // 竞赛分数 1416
    public int[] decrypt(int[] code, int k) {
        int[] res = new int[code.length];
        if (k == 0) {
            Arrays.fill(res, 0);
        } else if (k > 0) {
            int[] addCode = new int[code.length * 2];
            for (int i = 0; i < addCode.length; i ++) {
                addCode[i] = code[i % code.length];
            }

            int[] addSum = new int[addCode.length];
            addSum[0] = addCode[0];
            for (int i = 1; i < addSum.length; i ++ ) {
                addSum[i] = addSum[i - 1] + addCode[i];
            }
            for (int i = 0; i < res.length; i ++ ) {
                res[i] = addSum[i + k] - addSum[i];
            }
        } else {
            int[] decCode = new int[code.length * 2];
            for (int i = 0; i < decCode.length; i ++) {
                decCode[i] = code[i % code.length];
            }

            int[] decSum = new int[decCode.length];
            decSum[0] = decCode[0];
            for (int i = 1; i < decSum.length; i ++ ) {
                decSum[i] = decSum[i - 1] + decCode[i];
            }

            for (int i = code.length; i < code.length + res.length; i ++ ) {
                res[i - code.length] = decSum[i - 1] - decSum[i + k - 1];
            }
        }
        return res;
    }
}
