package com.alibaba.upscore;

/**
 * @author quanhangbo
 * @date 2024/2/8 15:40
 */
public class leetcode1170 {
    public static void main(String[] args) {

    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] w = new int[words.length];
        int[] v = new int[queries.length];
        int[] res = new int[queries.length];
        for (int i = 0; i < words.length; i ++ ) {
            String str = words[i];
            w[i] = freq(str);
        }

        for (int i = 0; i < queries.length; i ++ ) {
            int cur = 0;
            v[i] = freq(queries[i]);
            for (int j = 0; j < w.length; j ++ ) {
                if (v[i] < w[j]) {
                    cur ++;
                }
            }
            res[i] = cur;
        }
        return res;
    }

    public int freq(String str) {
        int[] cnt = new int[26];
        for (int i = 0; i < str.length(); i ++ ) {
            cnt[str.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < cnt.length; i ++ ) {
            if (cnt[i] != 0) {
                return cnt[i];
            }
        }
        return 0;
    }
}
