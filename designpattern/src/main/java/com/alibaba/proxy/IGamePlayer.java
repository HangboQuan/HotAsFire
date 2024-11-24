package com.alibaba.proxy;

/**
 * @author quanhangbo
 * @date 2024-11-24 23:37
 */
public interface IGamePlayer {

    // 登录
    public void login(String user, String password);

    // 打怪
    public void killBoss();

    // 升级
    public void upgrade();
}
