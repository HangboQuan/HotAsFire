package com.alibaba.weekly.w391;

/**
 * @author quanhangbo
 * @date 2024-03-31 21:35
 */
public class leetcode100263 {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int num = 0;
        int temp = x;
        while (x != 0) {
            num += x % 10;
            x /= 10;
        }

        if (temp % num == 0) {
            return num;
        }
        return -1;
    }
}
