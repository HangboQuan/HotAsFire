package com.sankuai.meituan.algorithm;

/**
 * @author quanhangbo
 * @date 2024/3/4 14:48
 */
public class quicksort {

    public static void main(String[] args) {
//        int[] ans = new int[]{3, 2, 5, 8, 4, 7, 6};
//        quick(ans, 0, ans.length - 1);
//        for (int v : ans) {
//            System.out.println(v);
//        }

        try {
            new Object().wait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void quick(int[] arr, int left, int right) {
        if (left < right) {
            int i, j, x;
            i = left;
            j = right;
            x = arr[i];
            while (i < j) {
                while (i < j && arr[j] > x) {
                    j --;
                }
                if (i < j) {
                    arr[i ++] = arr[j];
                }
                while (i < j && arr[i] < x) {
                    i ++;
                }
                if (i < j) {
                    arr[j --] = arr[i];
                }
            }
            arr[i] = x;
            quick(arr, left, i - 1);
            quick(arr, i + 1, right);
        }
    }
}
