package com.alibaba.javabase.work.annotation;

import com.alibaba.fastjson.JSONPath;
import com.alibaba.javabase.work.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author quanhangbo
 * @date 2024-05-24 21:00
 */
@Slf4j
@Component
public class ServiceRedirectInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ServiceRedirect serviceRedirect = invocation.getMethod().getAnnotation(ServiceRedirect.class);
        Object[] params = invocation.getArguments();
        Class<?> serviceImpClazz = serviceRedirect.serviceImplClazz();
        String serviceImplName = serviceImpClazz.getSimpleName();
        String methodName = serviceRedirect.methodName();

        String redirectPath = serviceRedirect.redirectPath();
        Object redirectValue = JSONPath.eval(params[0], redirectPath);

        if (redirectValue == null) {
            return invocation.proceed();
        }

        if (hitRedirectSwitch(methodName, String.valueOf(redirectValue))) { // 命中切流

            // 是否开启数据比对
            if (compareSwitch(serviceImplName, methodName)) {
                return getOldRespAndSaveMetric();
            } else {
                Object redirectImpl = SpringContextUtil.getBean(serviceImpClazz);
                Method method = serviceImpClazz.getMethod(methodName, serviceRedirect.paramsClazz());
                return method.invoke(redirectImpl, params);
            }
        }
        return invocation.proceed();
    }

    // 此处两个参数的作用：方法名和第一个入参 （第一个入参用于切流, 此处为userId）
    // 在配置平台配置 方法名-切流比例 如：userInfoDetailV2:100
    public boolean hitRedirectSwitch(String method, String redirectValue) {
        // 白名单 和 流量开关
        Integer factor = getFactorFromConfiguration(method);
        return hitWhiteList(redirectValue) || redirectNewPath(redirectValue, factor);
    }

    public boolean compareSwitch(String serviceImplName, String methodName) {
        return false;
    }

    public Integer getFactorFromConfiguration(String method) {
        return 100;
    }

    // 是否命中白名单
    public boolean hitWhiteList(String redirectValue) {
        return false;
    }

    public boolean redirectNewPath(String redirectValue, Integer factor) {
        if (factor >= 100) {
            // 推全（全部走新接口）
            return true;
        } else if (Strings.isNotBlank(redirectValue) && factor != 0) {
            try {
                return Long.parseLong(redirectValue) % 100L < (long)factor;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Object getOldRespAndSaveMetric(Class<?> serviceImplClazz, String methodName, Class<?> paramsClazz, Object[] params, String serviceImplName, MethodInvocation invocation) throws Throwable {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 异步调用新接口
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Object redirectImpl = SpringContextUtil.getBean(serviceImplClazz);
                Method method = serviceImplClazz.getMethod(methodName, paramsClazz);
                return method.invoke(redirectImpl, params);
            } catch (Exception e) {
                return null;
            }
        }, executorService);

        Object oldResp = invocation.proceed();

        CompletableFuture.runAsync(() -> {
            try {
                // 将数据保存到数据库
                // insert(traceId, service_name, method_name, params, oldResp);
                Map<String, Object> map = new HashMap<>();
                map.put("traceId", "traceId");
                map.put("service_name", serviceImplName);
            } catch (Exception e) {
                log.info("metric oldResp failed {}", e.toString());
            }
        },  executorService);

        CompletableFuture.runAsync(() -> {
            try {
                Object newResp = completableFuture.get();
                // insert(traceId, service_name, method_name, params, newResp);

            } catch (Exception e) {
                log.info("metric newResp failed {}", e.toString());
            }
        }, executorService);
        return oldResp;
    }
}
