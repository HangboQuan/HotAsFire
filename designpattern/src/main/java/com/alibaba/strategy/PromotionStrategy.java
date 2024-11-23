package com.alibaba.strategy;

public interface PromotionStrategy {
    double applyDiscount(double price);

    EnumPromotionType getPromotionType();
}
