package com.alibaba.javabase.work.annotation;
import java.lang.annotation.*;

/**
 * @author quanhangbo
 * @date 2024-05-24 20:36
 * 注解的元素类型必须是 基本类型、String、Class、枚举、注解或者上述类型的一维数组
 */
@Target(ElementType.METHOD) // 定义Annotation能够被应用于源码的位置
@Retention(RetentionPolicy.RUNTIME) // 定义Annotation的生命周期
@Documented
public @interface ServiceRedirect {

    String methodName() default "";

    Class<?> serviceImplClazz();

    Class<?>[] paramsClazz();

    String redirectPath();
}
