package com.alibaba.upscore;

/**
 * @author quanhangbo
 * @date 2024/2/7 17:50
 */
public class leetcode2559 {

    public static void main(String[] args) {

    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] target = new int[words.length];
        for (int i = 0; i < words.length; i ++ ) {
            String str = words[i];
            if (isTarget(str)) {
                target[i] = 1;
            }
        }
        int[] pre = new int[target.length + 1];
        for (int i = 1; i < pre.length; i ++ ) {
            pre[i] = pre[i - 1] + target[i - 1];
            System.out.println(pre[i]);
        }

        int[] res = new int[queries.length];
        int index = 0;
        for (int i = 0; i < queries.length; i ++ ) {
            int left = queries[i][0];
            int right = queries[i][1];
            res[index ++] = pre[right + 1] - pre[left];
        }
        return res;
    }

    public boolean isTarget(String str) {
        int i = 0;
        int j = str.length() - 1;
        return (str.charAt(i) == 'a' || str.charAt(i) == 'o' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'u') &&
                (str.charAt(j) == 'a' || str.charAt(j) == 'o' || str.charAt(j) == 'e' || str.charAt(j) == 'i' || str.charAt(j) == 'u');
    }
}
