package com.alibaba.javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author quanhangbo
 * @date 2023/10/4 15:05
 */
public class ProxyUtils {

    public static Star createProxy(BigStar star) {
        return (Star) Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(), new Class[]{Star.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sing")) {
                    System.out.println("搭建舞台，收取演唱会门票");
                } else if (method.getName().equals("actor")) {
                    System.out.println("搭建场景，action");
                }
                return method.invoke(star, args);
            }
        });
    }
}
