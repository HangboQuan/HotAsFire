package com.alibaba.topic.presum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/9/5 20:15
 */
public class PreSumModel {

    public static void main(String[] args) {
        // 给定一个数组，要求求出连续数组给定和为target，这样的数组有多少个
        int[] nums = {2, 6, 7, 3, 1, 7};
        int target = 8;
        System.out.println(SumOfTarget(nums, target));
        System.out.println(SumOfTargetPro(nums, target));

	    System.out.println(maxSum(Arrays.asList(2, 6, 7, 3, 1, 7), 3, 4));
	    int[] nums0 = {4, 4, 4};
	    System.out.println(maximumSubarraySum(nums0, 3));

	    System.out.println(numOfSubarrays(new int[]{1, 1, 1, 1, 1}, 1, 0));
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
     * 时间复杂度为O(N)
     */
    public static long maxSum(List<Integer> nums, int m, int k) {
//        long[] presum = getPreSum(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        long sum = 0L;
        long ans = 0L;
        for (int i = 0; i < nums.size(); i ++ ) {
        	int cur = nums.get(i);
        	sum += cur;
        	map.put(cur, map.getOrDefault(cur, 0) + 1);
        	// 用于维护一个大小的区间
        	if (i - k >= 0) {
				int pre = nums.get(i - k);
				sum -= pre;
				map.put(pre, map.get(pre) - 1);
				if (map.get(pre) == 0) {
					map.remove(pre);
				}
	        }
        	// 更新答案
        	if (map.size() >= m) {
        		ans = Math.max(ans, sum);
	        }
        }
        return ans;
    }

    public static long[] getPreSum(List<Integer> nums) {
    	long[] presum = new long[nums.size() + 1];
    	for (int i = 0; i < nums.size(); i ++ ) {
    		presum[i + 1] = presum[i] + nums.get(i);
	    }
    	return presum;
    }

	/**
	 *
	 * @param nums nums = [1,5,4,2,9,9,9]
	 * @param k k = 3
	 * @return 15
	 */
	public static long maximumSubarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		long sum = 0L;
		long ans = 0L;
		for (int i = 0; i < nums.length; i ++ ) {
			int cur = nums[i];
			sum += cur;
			map.put(cur, map.getOrDefault(cur, 0) + 1);
			if (i - k >= 0) {
				int pre = nums[i - k];
				sum -= pre;
				map.put(pre, map.get(pre) - 1);
				if (map.get(pre) == 0) {
					map.remove(pre);
				}
			}
			if (map.size() >= k) {
				ans = Math.max(ans, sum);
			}
		}
		return ans;
	}

	/**
	 * @param arr [11,13,17,23,29,31,7,5,2,3] [1,1,1,1,1]
	 * @param k 3 1
	 * @param threshold 5 0
	 * @return 6 5
	 */
	public static int numOfSubarrays(int[] arr, int k, int threshold) {
		long sum = 0L;
		int res = 0;
		for (int i = 0; i < arr.length; i ++ ) {
			int cur = arr[i];
			sum += cur;
			if (i - k >= 0) {
				sum -= arr[i - k];
				if (sum / k >= threshold) {
					res ++;
				}
			}
			if (i == k - 1 && sum / k >= threshold) {
				res ++;
			}
		}
		return res;
	}
}
