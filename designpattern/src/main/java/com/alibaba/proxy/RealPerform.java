package com.alibaba.proxy;

/**
 * @author quanhangbo
 * @date 2025-05-03 21:59
 */
public class RealPerform implements IPerform {
    @Override
    public void perform() {
        System.out.println("RealService is performing.");
    }
}
