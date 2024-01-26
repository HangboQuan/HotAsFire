package com.alibaba.leetcode_hot_100;

/**
 * @author quanhangbo
 * @date 2024/1/26 16:59
 */
public class 买卖股票的最佳时机 {

    // 7 1 5 3 6 4
    // 7 1 1 1 1 1
    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = prices[0];
        for (int price : prices) {
            ans = Math.max(ans, price - minPrice);
            if (price < minPrice) {
                minPrice = price;
            }
        }
        return ans;
    }
}
