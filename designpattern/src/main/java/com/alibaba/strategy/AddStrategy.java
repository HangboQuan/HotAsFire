package com.alibaba.strategy;

/**
 * @author quanhangbo
 * @date 2024-11-23 22:15
 */
public class AddStrategy implements CalculatorStrategy{


    @Override
    public int exec(int a, int b) {
        return a + b;
    }
}
