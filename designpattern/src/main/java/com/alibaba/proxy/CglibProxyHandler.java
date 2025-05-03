package com.alibaba.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author quanhangbo
 * @date 2025-05-03 22:14
 */
public class CglibProxyHandler implements MethodInterceptor {

    private Object target;

    public CglibProxyHandler(Object target) {
        this.target = target;
    }

    /**
     * 创建目标类的子类代理对象，并将当前拦截器设置为回调
     * @return
     */
    public Object getInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截目标对象的方法调用
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("Cglib proxy: " + method.getName() + " method executed.");
        return result;
    }
}
