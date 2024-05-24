package com.alibaba.javabase.work.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author quanhangbo
 * @date 2024-05-24 21:00
 */
@Component
public class ServiceRedirectInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ServiceRedirect serviceRedirect = invocation.getMethod().getAnnotation(ServiceRedirect.class);
        Object[] params = invocation.getArguments();
        Class<?> serviceImpClazz = serviceRedirect.serviceImplClass();
        String methodName = serviceRedirect.method();



        // 命中切流
        if (hitRedirectSwitch()) {

            // 是否开启数据比对
            if (compareSwitch()) {

            } else {
                Object redirectImpl = SpringContextUtil.getBean(serviceImpClazz);
                Method method = serviceImpClazz.getMethod(methodName, serviceRedirect.paramsClazz());
                return method.invoke(redirectImpl, params);
            }
        }
        return invocation.proceed();
    }

    public boolean hitRedirectSwitch() {
        // 白名单 和 流量开关
        return true;
    }

    public boolean compareSwitch() {
        return true;
    }
}
