package com.alibaba.proxy;

/**
 * @author quanhangbo
 * @date 2024-11-24 23:41
 */
public class GamePlayerProxy implements IGamePlayer{

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
