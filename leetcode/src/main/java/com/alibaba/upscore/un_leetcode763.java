package com.alibaba.upscore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024/2/7 16:07
 */
public class un_leetcode763 {

    public static void main(String[] args) {

    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int lastIndex = 0;
        int maxIndex = 0;
        int preIndex = 0;
        for (int i = 0; i < s.length(); i ++ ) {
            char c = s.charAt(i);
            for (int j = s.length() - 1; j >= 0; j -- ) {
                if (c == s.charAt(j)) {
                    lastIndex = j;
                    maxIndex = Math.max(maxIndex, lastIndex);
                }
            }
            if (i == maxIndex) {
                ans.add(maxIndex - preIndex + 1);
                preIndex = maxIndex + 1;
                maxIndex = 0;
            }
        }
        return ans;
    }
}
