package com.alibaba.weekly.w362;


import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2023/9/28 16:35
 */
public class leetcode1818 {

    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff1(new int[]{1, 10, 4, 4, 2, 7}, new int[]{9, 3, 5, 1, 7, 4}));
//        System.out.println(binarySearch(new int[]{1, 2, 4, 4, 7, 10}, 9));
        System.out.println(binarySearch2(new int[]{1, 2, 4, 4, 7, 10}, 9));
    }

    // 超时
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] sum = new int[nums1.length];
        int total = 0;
        for (int i = 0; i < nums1.length; i ++ ) {
            sum[i] = Math.abs(nums1[i] - nums2[i]);
            total = (total + sum[i]) % 1000000007;
        }
        int max = 0;
        for (int i = 0; i < nums2.length; i ++ ) {
            int target = Math.abs(nums1[i] - nums2[i]);
            for (int j = 0; j < nums1.length; j ++ ) {
                if (i != j) {
                   max = Math.max(target - Math.abs((nums1[j] - nums2[i])), max);
                }
            }
        }
        return total - max;
    }

    // 排序 + 二分
    public static int minAbsoluteSumDiff1(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = nums1.clone();
        Arrays.sort(rec);

        int total = 0, max = 0;
        for (int i = 0; i < n; i ++ ) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            total = (total + diff) % MOD;
            // 找到nums1数组中和nums2[i]最接近的
            int j = binarySearch2(rec, nums2[i]);
            if (diff > Math.abs(rec[j] - nums2[i])) {
                max = Math.max(max, diff - Math.abs(rec[j] - nums2[i]));
            }
        }
        return (total - max) % MOD;
    }

    public static int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int[] rec = nums1.clone();
        int n = nums1.length;
        Arrays.sort(rec);
        long total = 0, max = 0;
        for (int i = 0; i < n; i ++ ) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            total += diff;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (rec[mid] <= nums2[i]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }

            int nd = Math.abs(rec[r] - nums2[i]);
            if (r + 1 < n) {
                nd = Math.min(nd, Math.abs(rec[i + 1] - nums2[i]));
            }
            if (nd < diff) {
                max = Math.max(max, diff - nd);
            }
        }
        return (int)((total - max) % mod);

    }
    public static int binarySearch(int[] num, int target) {
        int l = 0, r = num.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (num[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static int binarySearch2(int[] rec, int target) {
        int low = 0, high = rec.length - 1;
        if (rec[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (rec[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


}
