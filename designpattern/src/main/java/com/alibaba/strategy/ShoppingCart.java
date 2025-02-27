package com.alibaba.strategy;

public class ShoppingCart {

    /**
     * 策略模式： 定义了一系列算法 将每个算法封装起来 并且使他们之间可以相互交换 是一种行为设计模式 它允许在运行时选择算法的行为
     * 策略模式的三种角色
     * 1. Context: 上下文，用于屏蔽上游模块对策略、算法的直接访问，封装可能存在的变化
     * 2. Strategy: 策略接口， 策略、算法家族的抽象，通常为接口，定义每个策略或者算法具有的方法和属性
     * 3， ConcreteStrategy: 具体策略，实现策略接口的具体算法
     *
     * 优点：
     * 1. 算法可以自由切换：只要实现抽象策略，通过封装角色对其进行封装，保证对外提供可自由切换的策略
     * 2. 避免使用多重条件判断：一个策略家族有5个策略算法，通过if-else判断，会导致代码臃肿，不易维护。使用策略模式，可以由上游模块决定采用哪种策略，
     * 策略家族对外提供的访问就是封装类，避免了多重条件判断
     * 3. 扩展性良好：策略模式提供了一种可插拔的方式，可以在不修改原有代码的情况下，增加新的策略算法（实现接口即可）
     *
     * 缺点：
     * 1. 策略类数量增多：每个策略都是一个类，复用性很小，类数量增加
     * 2. 所有策略类都要对外暴露：上游模块必须知道有哪些策略类，才能选择使用哪个策略
     * 比如在PromotionMain这个类中，需要知道BlackFridayPromotion和EndOfYearPromotion这两个策略类，才能选择使用哪个策略，这就要求上层模块了解策略的具体实现细节
     *（为了避免这种情况，可以使用工厂方法模式来创建策略实例，上层模块只需要知道策略的类型，而不需要了解具体的策略实现细节）
     *
     * 适用场景：
     * 1. 多个类只有在算法或行为上稍有不同的场景
     * 2. 算法需要自由切换的场景
     * 3. 需要屏蔽算法规则的场景
     *
     * 注意：
     * 1. 如果具体的策略数量超过4个，则考虑使用混合模式，解决策略类膨胀和对外暴露问题
     *
     * 实战:
     * 实际项目中我们一般通过工厂方法模式来实现策略类的声明
     */
    private PromotionStrategy promotionStrategy;

    public void setPromotionStrategy(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void setPromotionStrategy(EnumPromotionType promotionType) {
        this.promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(promotionType);
    }

    public double checkout(double totalPrice) {
        if (promotionStrategy != null) {
            totalPrice = promotionStrategy.applyDiscount(totalPrice);
        }
        return totalPrice;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 8, 4, 7, 6};
        quickSort(arr, 0, arr.length - 1);
        for (int v : arr) {
            System.out.println(v);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int x = arr[i];
            while (i < j && arr[j] > x) {
                j --;
            }
            if (i < j) arr[i ++] = arr[j];

            while (i < j && arr[i] < x) {
                i ++;
            }
            if (i < j) arr[j --] = arr[i];

            arr[i] = x;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
