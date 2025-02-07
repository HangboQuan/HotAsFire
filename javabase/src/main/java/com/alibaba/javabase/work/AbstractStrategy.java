package com.alibaba.javabase.work;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StopWatch;

/**
 * @author quanhangbo
 * @date 2025-01-20 21:25
 */
public abstract class AbstractStrategy<E extends Enum<E>, Aggregate, Extend> implements Strategy<E, Aggregate, Extend>, InitializingBean {

    private final Proxy<E, Aggregate, Extend> proxy;


    public AbstractStrategy(Proxy<E, Aggregate, Extend> proxy) {
        this.proxy = proxy;
    }

    @Override
    public void afterPropertiesSet() {
        proxy.registerStrategy(this);
    }

    @Override
    public Result<?> execute(Aggregate aggregate, Extend extend) {
        Result<?> result = Result.OK;
        StopWatch watch = new StopWatch();

        try {
            // 处理前准备 参数补全 参数校验
            prepare(aggregate, extend);
            // 前置处理 业务校验
            beforeExecute(aggregate, extend);
            // 执行核心流程
            result = doExecute(aggregate, extend);
        } catch (Exception e) {

        } catch (Throwable t) {
            result = Result.FAIL;
            throw t;
        } finally {
            try {
                // 后置处理
                afterExecute(aggregate, extend, result, result.isFail());
            } catch(Exception e) {

            } catch (Throwable t) {
                throw t;
            }
            watch.stop();
        }
        return result;
    }

    /**
     * 处理前准备
     * @param aggregate 聚合根
     * @param extend 扩展信息
     * @return
     */
    protected abstract Result<?> prepare(Aggregate aggregate, Extend extend);

    /**
     * 前置处理 失败则终止策略
     * @param aggregate
     * @param extend
     * @return
     */
    protected abstract Result<?> beforeExecute(Aggregate aggregate, Extend extend);
    /**
     * 核心逻辑
     * @param aggregate
     * @param extend
     * @return
     */
    protected abstract Result<?> doExecute(Aggregate aggregate, Extend extend);

    /**
     * 后置处理
     * @param aggregate
     * @param extend
     * @return
     */
    protected abstract Result<?> afterExecute(Aggregate aggregate, Extend extend, Result<?> result, Boolean failed);


}
