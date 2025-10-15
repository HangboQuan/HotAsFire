package com.alibaba.proxy;

/**
 * @author quanhangbo
 * @date 2024-11-24 23:41
 */
public class GamePlayerProxy implements IGamePlayer{


    /**
     * 代理模式：
     * 1. 控制访问：控制对目的对象的访问，在访问前进行权限检查或者其他预处理操作
     * 2. 延迟加载：在需要时才创建对象
     * 3. 增强功能：不修改目标对象的情况下增强目标对象的功能
     * 4. 远程代理：为远程对象提供本地带来，隐藏网络通信细节
     * 一句话描述：用在不改变原有业务逻辑下，给目标对象增加额外功能.
     * 在业务中做一些增强（例如：鉴权、缓存、日志、限流等），在不改动原代码的情况下
     */
    private IGamePlayer gamePlayer;

    public GamePlayerProxy(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    // 代练登录
    @Override
    public void login(String user, String password) {
        gamePlayer.login(user, password);
    }

    // 代练打怪
    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }

    // 代练升级
    @Override
    public void upgrade() {
        gamePlayer.upgrade();
    }
}
