package com.alibaba.javabase.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quanhangbo
 * @date 2024/1/8 15:43
 */
public class FailFast {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(5, 15);
        map.put(1, 10);
        map.put(2, 20);
        map.put(10, 20);

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getKey() % 5 == 0) {
                map.remove(m.getKey());
            }
        }

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}
