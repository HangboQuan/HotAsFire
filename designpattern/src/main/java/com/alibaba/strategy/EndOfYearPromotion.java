package com.alibaba.strategy;

public class EndOfYearPromotion implements PromotionStrategy {

    /**
     * 年终促销7折
     * @param price
     * @return
     */
    @Override
    public double applyDiscount(double price) {
        return price * 0.7;
    }

    @Override
    public EnumPromotionType getPromotionType() {
        return EnumPromotionType.END_OF_YEAR;
    }
}
