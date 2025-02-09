package com.alibaba.strategy;

public class PromotionMain {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPromotionStrategy(new BlackFridayPromotion());
        double totalPrice = 100;
        double finalPrice = cart.checkout(totalPrice);
        System.out.println("after end of black friday promotion:" + finalPrice);

        cart.setPromotionStrategy(new EndOfYearPromotion());
        double totalPrice1 = 200;
        double finalPrice1 = cart.checkout(totalPrice1);
        System.out.println("after end of year promotion:" + finalPrice1);
        
        PromotionContext blackContext = new PromotionContext(new BlackFridayPromotion());
        blackContext.executePromotionStrategy(100);
        System.out.println("after end of black friday promotion:" + finalPrice);


        PromotionContext endOfYearContext = new PromotionContext(new EndOfYearPromotion());
        endOfYearContext.executePromotionStrategy(200);
        System.out.println("after end of year promotion:" + finalPrice1);


        cart.setPromotionStrategy(EnumPromotionType.BLACK_FRIDAY);
        System.out.println(cart.checkout(100));


        cart.setPromotionStrategy(EnumPromotionType.END_OF_YEAR);
        System.out.println(cart.checkout(200));


    }
}
