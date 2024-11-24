package com.alibaba.proxy;

/**
 * @author quanhangbo
 * @date 2024-11-24 23:38
 */
public class GamePlayer implements IGamePlayer{

    private String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println(this.name + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "升级");
    }
}
