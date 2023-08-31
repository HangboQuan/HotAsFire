package com.alibaba.weekly.w360;

/**
 * @author quanhangbo
 * @date 2023/8/27 10:49
 */
public class leetcode8015 {

    public long minimumPossibleSum(int n, int target) {
        long sum = 0;
        int offset;
        if (target % 2 == 0) {
            offset = target / 2 - 1;
        } else {
            offset = target / 2;
        }

        if (2 * n - 1 < target) {
            offset = 0;
        }

        for (int i = 0; i < n + offset; i ++ ) {
            sum += i + 1;
        }

        while (offset > 0) {
            sum -= -- target;
            offset --;
        }
        return sum;
    }

    public int furthestDistanceFromOrigin(String moves) {
        int left = 0;
        int right = 0;
        int mid = 0;

        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                left --;
            } else if (move == 'R') {
                right ++;
            } else {
                mid ++;
            }

        }
        return Math.abs(left + right) + mid;
    }
}
