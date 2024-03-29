package com.alibaba.strategy;

public class BlackFridayPromotion implements PromotionStrategy {

    /**
     * 黑色星期五 打五折
     * @param price
     * @return
     */
    @Override
    public double applyDiscount(double price) {
        return price * 0.5;
    }
}
