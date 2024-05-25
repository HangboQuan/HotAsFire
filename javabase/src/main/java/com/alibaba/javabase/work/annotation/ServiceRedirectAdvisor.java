package com.alibaba.javabase.work.annotation;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author quanhangbo
 * @date 2024-05-25 16:11
 */
@Component
public class ServiceRedirectAdvisor extends AbstractPointcutAdvisor {
    private final StaticMethodMatcherPointcut pointCut = new StaticMethodMatcherPointcut() {

        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.isAnnotationPresent(ServiceRedirect.class);
        }
    };

    public ServiceRedirectAdvisor() {

    }
    @Resource
    private ServiceRedirectInterceptor serviceRedirectInterceptor;

    @Override
    public Pointcut getPointcut() {
        return this.pointCut;
    }

    @Override
    public Advice getAdvice() {
        return this.serviceRedirectInterceptor;
    }
}
