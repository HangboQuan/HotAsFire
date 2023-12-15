package com.alibaba.javabase.proxy;

/**
 * @author quanhangbo
 * @date 2023/10/4 15:03
 */
public class BigStar implements Star {

    private String name;

    public BigStar(String name) {
        this.name = name;
    }

    @Override
    public String sing(String name) {
        System.out.println(this.name + "正在唱" + name);
        return "谢谢！";
    }

    @Override
    public void actor() {
        System.out.println(this.name + "正在演戏");
    }
}
