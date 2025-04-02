package com.alibaba.javabase.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2025-03-10 17:02
 */
public class Demo {

    /**
     * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A > B 的索引 i 的数目来描述。
     * 返回 A 的任意排列，使其相对于 B 的优势最大化。
     * 示例 1：
     * 输入：A = [2,7,11,15], B = [1,10,4,11]
     * 输出：[2,11,7,15]
     *
     * 示例 2：
     * 输入：A = [12,24,8,32], B = [13,25,32,11]
     * 输出：[24,32,8,12]
     *
     * 提示： 1 <= A.length = B.length <= 10000 0 <= A <= 10^9 0 <= B <= 10^9
     */

    public static void main(String[] args) {
        int[] A = {2, 7, 11, 15};
        int[] B = {1, 10, 4, 11};

        int[] cpA = Arrays.copyOf(A, A.length);
        int[] cpB = Arrays.copyOf(B, B.length);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < cpB.length; i ++) {
            map.put(cpB[i], i);
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int index = 0;
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i ++) {
            if (map.containsKey(B[i])) {
                res[index ++] = cpA[map.get(B[i])];
            }
        }

        for (int re : res) {
            System.out.println(re);
        }

    }
}
