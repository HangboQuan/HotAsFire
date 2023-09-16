package com.alibaba.weekly.dw112;

/**
 * @author quanhangbo
 * @date 23-9-16 下午4:32
 */
public class leetcode2806 {

	// 模拟
	public int accountBalanceAfterPurchase(int purchaseAmount) {
		int[] nums = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		int max = Integer.MAX_VALUE;
		int target = nums[0];
		for (int i = 0; i < nums.length; i ++ ) {
			if (Math.abs(nums[i] - purchaseAmount) <= max) {
				max = Math.abs(nums[i] - purchaseAmount);
				target = nums[i];
			}
		}
		return 100 - target;
	}
}
