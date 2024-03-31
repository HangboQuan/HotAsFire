package com.alibaba.weekly.w391;

/**
 * @author quanhangbo
 * @date 2024-03-31 21:36
 */
public class leetcode100235 {

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        if (numBottles < numExchange) {
            return numBottles;
        }
        int cnt = 0;
        int temp = numBottles;
        while (numBottles >= 0) {
            int left = numBottles - numExchange;
            if (left + 1 <= 0) {
                break;
            }
            cnt += 1;
            numExchange += 1;
            numBottles = left + 1;
        }

        return temp + cnt;
    }
}
