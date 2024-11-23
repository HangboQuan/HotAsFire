package com.alibaba.strategy;

/**
 * @author quanhangbo
 * @date 2024-11-23 22:18
 */
public class CalculateContext {

    private CalculatorStrategy calculatorStrategy;

    public CalculateContext(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    public int executeStrategy(int a, int b) {
        return this.calculatorStrategy.exec(a, b);
    }
}
