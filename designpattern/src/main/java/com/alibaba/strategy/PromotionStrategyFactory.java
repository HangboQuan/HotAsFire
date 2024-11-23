package com.alibaba.strategy;

/**
 * @author quanhangbo
 * @date 2024-11-23 20:53
 */
public class PromotionStrategyFactory {

    public static PromotionStrategy getPromotionStrategy(EnumPromotionType promotionType) {
        if (promotionType == null) {
            return null;
        }
        switch (promotionType) {
            case BLACK_FRIDAY:
                return new BlackFridayPromotion();
            case END_OF_YEAR:
                return new EndOfYearPromotion();
            default:
                return null;
        }
    }
}
