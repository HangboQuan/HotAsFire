package com.alibaba.weekly.w385;

/**
 * @author quanhangbo
 * @date 2024/2/18 10:28
 */
public class leetcode100212 {
    public static void main(String[] args) {

    }

    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i ++ ) {
            for (int j = i + 1; j < words.length; j ++ ) {
                if (preSuffix(words[i], words[j])) {
                    count ++;
                }
            }
        }
        return count;
    }

    public boolean preSuffix(String str1, String str2) {
        return str2.startsWith(str1) && str2.endsWith(str1);
    }

}
