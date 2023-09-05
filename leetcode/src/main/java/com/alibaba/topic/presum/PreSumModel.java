package com.alibaba.topic.presum;

import java.util.HashMap;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/9/5 20:15
 */
public class PreSumModel {

    public static void main(String[] args) {
        // 给定一个数组，要求求出连续数组给定和为target，这样的数组有多少个
        int[] nums = {1, 6, 2, -2, 4, 1};
        int target = 8;
        System.out.println(SumOfTarget(nums, target));
        System.out.println(SumOfTargetPro(nums, target));
    }

    public static int SumOfTarget(int[] nums, int target) {
        // 暴力 前缀和 其中presum[i]表示: [0, i]的和
        int[] presum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i ++ ) {
            presum[i + 1] = presum[i] + nums[i];
        }
        int ans = 0;
        // nums[i, j]的和(i < j) presum[j + 1] - presum[i]
        for (int i = 1; i <= nums.length; i ++ ) {
            for (int j = 0; j < i; j ++ ) {
                if (presum[i] - presum[j] == target) {
                    ans ++;
                }
            }
        }
        return ans;
    }

    public static int SumOfTargetPro(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int sumI = 0;
        // 必须要有这行代码，用于处理前缀和数组和target元素相等的情况
        map.put(0, 1);
        for (int i = 0; i < nums.length; i ++ ) {
            // 前i项和
            sumI += nums[i];
            // 满足target的数组区间 => 前j项和
            int sumJ = sumI - target;
            if (map.containsKey(sumJ)) {
                System.out.print(sumJ + " ");
                ans += map.get(sumJ);
            }
            map.put(sumI, map.getOrDefault(sumI, 0) + 1);
        }
        return ans;
    }

    /**
     * 给定一个整数nums数组和两个整数m和k，返回nums数组中长度为k的几乎唯一子数组的最大和，
     * 如果一个子数组有至少m个不同元素，则称为几乎唯一子数组
     * @param nums [2, 6, 7, 3, 1, 7]
     * @param m 3
     * @param k 4
     * @return 18
     */
    public static long maxSum(List<Integer> nums, int m, int k) {
        int[] cnt = new int[10];
        int[] presum = new int[nums.size() + 1];
        for (int i = k, j = 0; i <= nums.size(); i ++ ) {
            presum[i] = presum[i - 1] + nums.get(i);
            boolean unique = false;


        }
    }
}
