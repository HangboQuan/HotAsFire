package com.alibaba.javabase.base;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

//        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
//            if (m.getKey() % 5 == 0) {
//                map.remove(m.getKey());
//            }
//        }
//
//        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
//            System.out.println(m.getKey() + " " + m.getValue());
//        }

        Iterator iterator = map.entrySet().iterator();
        Map.Entry entry;

        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            if ((Integer)entry.getKey() % 5 == 0) {
                map.remove(entry.getKey());
            }
        }



        // 为什么iterator迭代器 能够避免fail-fast
        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, Integer> entry1 = iterator1.next();
            if (entry1.getKey() % 5 == 0) {
                iterator1.remove();
            }
        }

        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> e = it.next();
            System.out.println(e.getKey() + " " + e.getValue());
        }


        List<String> ans = new ArrayList<String>() {
                {
                        add("abc");
                        add("alibaba");
                        add("pdd");
                        add("bytedance");
                }

        };

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap<String, String> map1 = new HashMap<>();


    }
}
