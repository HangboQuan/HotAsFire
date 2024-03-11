package org.alibaba.taobao.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024/3/11 20:22
 */
public class ArrayListTest {



    public static void main(String[] args) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        ans.add(2);

        int[] t1 = {1, 3};
        int[] t2 = Arrays.copyOf(t1, 4);
        for (int v : t2) {
            System.out.println(v);
        }
    }
}
