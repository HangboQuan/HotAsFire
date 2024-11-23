package com.alibaba.strategy;

/**
 * @author quanhangbo
 * todo 这个其实是多余的，和ShoppingCart的功能一样，都是为了实验策略模式下的Context上下文的封装，避免上游模块直接对策略、算法的访问，并封装可能存在的变化
 * @date 2024-11-23 16:45
 */
public class PromotionContext {

    private PromotionStrategy promotionStrategy;

    public PromotionContext(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }


    public double executePromotionStrategy(double price) {
        return this.promotionStrategy.applyDiscount(price);
    }
}
