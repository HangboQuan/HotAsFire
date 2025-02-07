package com.alibaba.javabase.work.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.zhipu.oapi.utils.StringUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quanhangbo
 * @date 2025-01-18 14:51
 */
public class TtlUtil {

    private static final TransmittableThreadLocal<Map<String, Object>> context = new TransmittableThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new ConcurrentHashMap<>(8);
        }

        @Override
        protected Map<String, Object> childValue(Map<String, Object> parentValue) {
            return new ConcurrentHashMap<>(parentValue);
        }
    };


    private static final TransmittableThreadLocal<String> startTraceId = new TransmittableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "";
        }

        @Override
        protected String childValue(String parentValue) {
            return initialValue();
        }
    };

    private static final TransmittableThreadLocal<Boolean> closeAttachTag = new TransmittableThreadLocal<Boolean>() {
        @Override
        protected Boolean childValue(Boolean parentValue) {
            return initialValue();
        }

        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    public static void start(String key) {
        try {
            if (!isStart(key)) {
                clean();
                startTraceId.set(key);
            }
        } catch (Exception e) {

        }
    }
    public static boolean isStart(String key) {
        return StringUtils.isNotEmpty(key) && key.equals(startTraceId.get());
    }

    public static void clean() {
        startTraceId.remove();
        context.remove();
    }

    public static void set(String key, Object value) {
        context.get().put(key, value);
    }

    public static Object get(String key, Object defaultValue) {
        return context.get().getOrDefault(key, defaultValue);
    }

    public static Object remove(String key) {
        return context.get().remove(key);
    }

    public static boolean closeAttach() {
        return BooleanUtils.isTrue(closeAttachTag.get());
    }

}
