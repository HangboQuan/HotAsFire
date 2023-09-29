package com.alibaba.topic.halfsearch;

/**
 * @author quanhangbo
 * @date 2023/9/29 23:54
 */
public class LCR173 {
    public int takeAttendance(int[] records) {
        // int total = (records.length * (records.length + 1) / 2);
        // int sum = 0;
        // for (int i = 0; i < records.length; i ++ ) {
        //     sum += records[i];
        // }
        // return total - sum;

        // 二段性

        int l = 0, r = records.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (records[mid] == mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return records[r] != r ? r : r + 1;

    }
}
