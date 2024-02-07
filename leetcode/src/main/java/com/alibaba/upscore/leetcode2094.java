package com.alibaba.upscore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author quanhangbo
 * @date 2024/2/7 16:08
 */
public class leetcode2094 {

    public static void main(String[] args) {

    }

    public int[] findEvenNumbers(int[] digits) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < digits.length; i ++ ) {
            for (int j = 0; j < digits.length; j ++ ) {
                for (int k = 0; k < digits.length; k ++ ) {
                    if (i != j && j != k && i != k && digits[i] != 0) {
                        int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                        if ((num & 1) == 0) {
                            ans.add(num);
                        }
                    }
                }
            }
        }
        Collections.sort(ans);
        Set<Integer> set = new HashSet(ans);
        ArrayList<Integer> ans0 = new ArrayList<>(set);
        Collections.sort(ans0);
        int[] res = new int[ans0.size()];
        int index = 0;
        for (Integer value : ans0) {
            res[index ++] = value;
        }
        return res;
    }
}
