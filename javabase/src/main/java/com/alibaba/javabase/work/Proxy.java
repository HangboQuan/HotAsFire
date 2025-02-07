package com.alibaba.javabase.work;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:27
 */
@Slf4j
public abstract class Proxy<E extends Enum<E>, Req, Ext> {

    private static final Logger logger = LoggerFactory.getLogger(Proxy.class);
    private final Map<E, Strategy<E, Req, Ext>> enum2Strategy = new HashMap<>();

    public Strategy<E, Req, Ext> getStrategy(E type) {
        return enum2Strategy.get(type);
    }

    public void registerStrategy(Strategy<E, Req, Ext> strategy) {
        strategy.getStrategyTypes().forEach(t -> {
            if (enum2Strategy.containsKey(t)) {
                logger.info("strategy {} already exists", t);
                return ;
            }
            enum2Strategy.put(t, strategy);
        });
    }

}
