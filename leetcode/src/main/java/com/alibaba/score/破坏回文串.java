package com.alibaba.score;

import java.util.*;

/**
 * @author quanhangbo
 * @date 23-11-14 上午10:29
 */
public class 破坏回文串 {

    public String breakPalindrome(String palindrome) {
        char[] ch = palindrome.toCharArray();
        if (ch.length <= 1) {
            return "";
        }
        for (int i = 0; i < ch.length; i ++ ) {
            char tmp = ch[i];
            if (ch[i] != 'a') {
                ch[i] = 'a';
                if (isPalindRome(new String(ch))) {
                    return new String(ch);
                } else {
                    ch[i] = tmp;
                }
            }
        }
        return "a" + palindrome.substring(1);
    }


    public boolean isPalindRome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] ans = {{2,4}, {8,9}, {4, 5}, {3, 6}, {5, 11}};
        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        int left = ans[0][0];
        int right = ans[0][1];
        for (int i = 1; i < ans.length; i ++ ) {
            if (ans[i][0] <= right) {
                right = Math.max(ans[i][1], right);
            } else {
                res.add(new int[]{left, right});
                left = ans[i][0];
                right = ans[i][1];
            }
        }

        if (res.size() > 0 && left < res.get(res.size() - 1)[0]) {
            // 继续更新
            int A = left;
            int B = res.get(res.size() - 1)[1];
            res.remove(res.size() - 1);
            res.add(new int[]{A, B});
        } else {
            res.add(new int[]{left, right});
        }

        for (int[] a : res) {
            for (int v : a) {
                System.out.print(v + " ");
            }
        }
    }
}