package com.hangbo.javarewritesource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2024/1/29 16:24
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println(test(99));
        System.out.println(mytest(new int[]{1, 3, 4, 5, 9}, new int[]{3, 4, 5, 6, 8}));
    }

    public static int test(int n) {
        int[] count = {5, 20, 50, 100, 500, 1000, 2000, 3000, 4000, 5000, 6000};
        int[] money = {30, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int sum = 0;
        if (n == 0) {
            return 0;
        }
        for (int i = 0; i < count.length; i ++ ) {
            if (n >= count[i]) {
                int index = i + 1;
                for (int j = 0; j < index; j ++ ) {
                    sum += count[i] * money[j];
                }
                sum += (n - count[i]) * money[index];
                break;
            }
            else {
                sum = n * money[0];
            }

        }
        return sum;
    }


    //  1, 3, 5, 7, 9
    //  1, 3, 4, 5, 6

    //  result: 1 3 5
    public static List<Integer> mytest(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();
        if (arr1.length < arr2.length) {
            return mytest(arr2, arr1);
        }

        int index1 = 0;
        int index2 = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                index1 ++;
            } else if (arr1[index1] > arr2[index2]) {
                index2 ++;
            } else {
                res.add(arr2[index2]);
                index1 ++;
                index2 ++;
            }
        }
        return res;
    }
}
