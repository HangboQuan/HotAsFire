package com.alibaba.topic.twopointer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024/1/25 14:37
 */
public class 回文子串的数量 {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abaaba"));
        System.out.println(countSubstrings("alibaba"));
    }

    public static int countSubstrings(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i ++ ) {
            res += isPalind(s, i, i);
            res += isPalind(s, i, i + 1);
        }
        return res;
    }

    public static int isPalind(String s, int i, int j) {
        int ans = 0;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
            ans ++;
            i --;
            j ++;
        }
        return ans;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x >= 0 && x <= 9) {
            return true;
        }
        List<Integer> ans = new ArrayList<>();
        while (x != 0) {
            ans.add(x % 10);
            x /= 10;
        }

        int mid = ans.size() / 2;
        System.out.println(mid);
        for (int i = 0; i <= mid; i ++ ) {
            if (ans.get(i) != ans.get(ans.size() - 1 - i)) {
                return false;
            }
        }
        return true;


    }
}
