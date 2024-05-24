package com.alibaba.javabase.work.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author quanhangbo
 * @date 2024-05-24 21:00
 */
public class ServiceRedirectInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ServiceRedirect serviceRedirect = invocation.getMethod().getAnnotation(ServiceRedirect.class);
        Object[] params = invocation.getArguments();
        Class<?> serviceImpClazz = serviceRedirect.serviceImplClass();

        // 命中切流
        if (hitRedirectSwitch()) {

            // 切流占比
            if (redirectPercentage()) {

            }
        }



        return invocation.proceed();
    }

    public boolean hitRedirectSwitch() {
        return true;
    }

    public boolean redirectPercentage() {
        return true;
    }
}
