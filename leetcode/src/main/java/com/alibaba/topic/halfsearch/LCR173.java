package com.alibaba.topic.halfsearch;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            // 查找最大值的下标
            if (mid != 0 && mid != nums.length - 1) {
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        return r;
    }

    public int minOperations(List<Integer> nums, int k) {

        return 0;
    }

    public boolean isMatch(List<Integer> nums, int n, int m, int k) {
        int count = 0;
        for (int i = n; i <= m; i ++ ) {
            if (nums.contains(i)) {
                count ++;
            }
        }
        return count == k;
    }




    public static void main(String[] args) {

    }
}
