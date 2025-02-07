package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:07
 */
public interface Identifiable<ID extends Identifier>{

    ID getId();
}
