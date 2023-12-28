package com.alibaba.interview;

import java.util.Arrays;

/**
 * @author quanhangbo
 * @date 2023/12/28 11:04
 */
public class 归并排序 {

    /**
     * 归并排序使用的是分治算法，先将问题分解为小问题求解，在将小问题得到的答案合并起来
     * 时间复杂度O(nlogn)
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 8, 4, 7, 6};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t ++] = arr[i ++];
            } else {
                temp[t ++] = arr[j ++];
            }
        }

        while (i <= mid) {
            temp[t ++] = arr[i ++];
        }
        while (j <= right) {
            temp[t ++] = arr[j ++];
        }
        t = 0;
        while (left <= right) {
            arr[left ++] = temp[t ++];
        }


    }


}
