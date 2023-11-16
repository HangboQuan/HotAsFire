package com.alibaba.interview.bytedance;

/**
 * @author quanhangbo
 * @date 2023/11/16 9:37
 */
public class 接雨水 {
    // height = [0,1,0,2,1,0,1,3,2,1,2,1]
    public int trap(int[] height) {
        // 记录每个元素左边区间的最大值和右边区间的最大值
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i ++ ) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i -- ) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int sum = 0;
        for (int i = 0; i < height.length; i ++ ) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }
}
