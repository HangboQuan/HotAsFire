package com.alibaba.proxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author quanhangbo
 * @date 2025-05-03 22:10
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object target;


    /**
     * JDK动态代理
     * @param target
     */
    public DynamicProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object result = method.invoke(target, args);
        System.out.println("结束代理");
        return result;
    }
}
