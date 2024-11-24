package com.alibaba.proxy;

import java.sql.SQLOutput;

/**
 * @author quanhangbo
 * @date 2024-11-24 23:40
 */
public class GamePlayerMain {

    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("quanhangbo");

        IGamePlayer proxy = new GamePlayerProxy(player);

        // 真实角色
        player.login("quanhanbgo", "password");
        player.killBoss();
        player.upgrade();

        System.out.println("====================================");

        // 代练角色
        proxy.login("quanhangbo", "password");
        proxy.killBoss();
        proxy.upgrade();
    }
}
