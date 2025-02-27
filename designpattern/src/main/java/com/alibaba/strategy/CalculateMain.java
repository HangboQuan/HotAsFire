package com.alibaba.strategy;

import java.util.HashMap;

/**
 * @author quanhangbo
 * @date 2024-11-23 22:18
 */
public class CalculateMain {

    public static void main(String[] args) {
        CalculateContext context = new CalculateContext(new AddStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new CalculateContext(new SubStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        HashMap map = new HashMap();

        ThreadLocal threadLocal = new ThreadLocal();


        System.out.println(EnumCalculator.ADD.exec(10, 5));
        System.out.println(EnumCalculator.SUB.exec(10, 5));
    }
}
