package com.alibaba.topic.twopointer;

import java.util.HashSet;

/**
 * @author quanhangbo
 * @date 2023/8/23 20:51
 */
public class leetcode283 {

    public static void main(String[] args) {
        int[] ans = {0, 1, 0, 3, 12};
        moveZeroes(ans);

        for (int i = 0; i < ans.length; i ++ ) {
            System.out.println(ans[i]);
        }

    }

    public static void moveZeroes(int[] nums) {
        for (int i = nums.length - 1, j = nums.length - 1; i >= 0; ) {
            if (nums[j] == 0) {
                j --;
            }
            if (nums[i] == 0) {
                swap(nums, i --, j);
            } else {
                i --;
            }
        }
    }

    public static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }


    public String solution(String password) {
        // 在这⾥写代码
        if (threePass(password) && lenPass(password) &&
                buildPass(password)) {
            return "strong";
        }
        return "weak";
    }

    public boolean threePass(String password) {
        if (password.length() < 3) {
            return true;
        }
        for (int i = 0; i < password.length() - 3; i ++ ) {
            String str = password.substring(i, i + 3);
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < str.length(); j ++ ) {
                set.add(password.charAt(j));
            }
            if (set.size() == 1) {
                return false;
            }
        }
        return true;
    }
    public boolean lenPass(String password) {
        return password.length() >= 8 && password.length() <= 22;
    }
    public boolean buildPass(String password) {
        boolean less = false;
        boolean more = false;
        boolean num = false;

        for (int i = 0; i < password.length(); i ++ ) {
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                less = true;
            }
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                more = true;
            }
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                num = true;
            }
        }
        return less && more && num;
    }
}
