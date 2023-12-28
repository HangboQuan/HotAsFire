package com.alibaba.interview;

/**
 * @author quanhangbo
 * @date 2023/12/28 10:44
 */
public class 快速排序 {

    /**
     * 选择一个基准数，通过一趟排序使小于基准数的元素排在基准数的左边，大于基准数的元素排在基准数的右边；
     * 然后再把左子区间和右子区间分别用这种方式进行排序，整个过程用递归来实现
     *
     * 不稳定排序 时间复杂度O(nlogn)
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找基准
            int i, j, x;
            i = left;
            j = right;
            x = arr[i];

            while (i < j) {
                // arr[j] 从右向左第一个小于x的数
                while (i < j && arr[j] > x) {
                    j --;
                }
                if (i < j) {
                    arr[i ++] = arr[j];
                }

                // arr[i] 从左到右第一个大于x的数
                while (i < j && arr[i] < x) {
                    i ++;
                }
                if (i < j) {
                    arr[j --] = arr[i];
                }
            }
            // 赋值
            arr[i] = x;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
    public static void main(String[] args) {
        int[] arr = {30, 40, 60, 10, 20, 50};
        quickSort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
