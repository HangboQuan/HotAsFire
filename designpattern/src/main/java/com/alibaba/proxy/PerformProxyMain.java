package com.alibaba.proxy;

import java.lang.reflect.Proxy;

/**
 * @author quanhangbo
 * @date 2025-05-03 22:11
 */
public class PerformProxyMain {

    public static void main(String[] args) {
        IPerform performer = new RealPerform();

        IPerform proxy = (IPerform) Proxy.newProxyInstance(performer.getClass().getClassLoader(),
                performer.getClass().getInterfaces(),
                new DynamicProxyHandler(performer));
        proxy.perform();

        IPerform proxy1 = (IPerform) new CglibProxyHandler(performer).getInstance();
        proxy1.perform();
    }
}
