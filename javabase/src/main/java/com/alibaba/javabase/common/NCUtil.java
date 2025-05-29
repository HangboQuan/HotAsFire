package com.alibaba.javabase.common;
import java.util.function.Supplier;

/**
 * @author quanhangbo
 * @date 2025-05-17 23:18
 */
public class NCUtil<T> {

    public static <T> T ofNullable(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }
}
