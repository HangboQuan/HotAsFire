package com.alibaba.strategy;

public class ShoppingCart {

    /**
     * 策略模式：是一种行为设计模式 它允许在运行时选择算法的行为 定义了一系列算法 将每个算法封装起来 并使它们相互替换
     *
     * 适用场景：
     * 1. 当想使用对象中各种不同的算法变体，并希望能在运行时切换算法时，使用策略模式
     */
    private PromotionStrategy promotionStrategy;

    public void setPromotionStrategy(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public double checkout(double totalPrice) {
        if (promotionStrategy != null) {
            totalPrice = promotionStrategy.applyDiscount(totalPrice);
        }
        return totalPrice;
    }
}
