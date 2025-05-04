package com.alibaba.principle;

/**
 * @author quanhangbo
 * @date 2025-05-04 22:56
 */
public class Soldier {
    private AbstractGun gun;

    public Soldier(AbstractGun gun) {
        this.gun = gun;
    }

    /**
     * 杀敌
     */
    public void killEnemy() {
        System.out.println("士兵开始杀敌");
        gun.shoot();
    }
}
