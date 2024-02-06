package com.hangbo.javarewritesource;

/**
 * @author quanhangbo
 * @date 2024/2/2 15:37
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 8, 4, 7, 6};
        quickSort(arr, 0, arr.length - 1);

        for (int v : arr) {
            System.out.println(v);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int x = arr[i];
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
            arr[i] = x;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
