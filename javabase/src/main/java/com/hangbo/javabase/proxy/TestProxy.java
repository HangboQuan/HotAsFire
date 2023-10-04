package com.hangbo.javabase.proxy;

/**
 * @author quanhangbo
 * @date 2023/10/4 15:15
 */
public class TestProxy {

    public static void main(String[] args) {

        Star bigStar = ProxyUtils.createProxy(new BigStar("刘德华"));
        System.out.println(bigStar.sing("忘情水"));

        bigStar.actor();
    }
}
