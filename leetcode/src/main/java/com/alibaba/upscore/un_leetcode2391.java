package com.alibaba.upscore;

/**
 * @author quanhangbo
 * @date 2024/2/7 17:32
 */
public class un_leetcode2391 {

    public static void main(String[] args) {

    }
    public int garbageCollection(String[] garbage, int[] travel) {
        int total = 0;
        int mLast = 0;
        int pLast = 0;
        int gLast = 0;
        for (int i = 0; i < garbage.length; i ++ ) {
            char[] ch = garbage[i].toCharArray();
            total += ch.length;
            for (char c : ch) {
                if (c == 'M') {
                    mLast = i;
                }
                if (c == 'P') {
                    pLast = i;
                }
                if (c == 'G') {
                    gLast = i;
                }
            }
        }
        if (mLast > 0) {
            for (int i = 0; i < mLast; i ++ ) {
                total += travel[i];
            }
        }
        if (pLast > 0) {
            for (int i = 0; i < pLast; i ++ ) {
                total += travel[i];
            }
        }
        if (gLast > 0) {
            for (int i = 0; i < gLast; i ++ ) {
                total += travel[i];
            }
        }
        return total;
    }

}
