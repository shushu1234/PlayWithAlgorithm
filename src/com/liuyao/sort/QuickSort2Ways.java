package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/15 18:26.
 */
public class QuickSort2Ways {
    private QuickSort2Ways() {

    }

    private static int partition(Comparable[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        int i = l + 1, j = r;

        while (true) {
//            当从前向后遇到大于等于v的数就停止
            while (i <= r && arr[i].compareTo(v) < 0) {
                i++;
            }

//            当从后向前遇到小于等于v的数就停止

            while (j >= l + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }

            if (i > j) {
                break;
            }
//            前后交换
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
        return j;
    }

    private static void sort(Comparable[] arr, int l, int r) {
//        if (r-l<=15){
//            new Insertion().sort(arr,l,r);
//        }

        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, 0, p - 1);
        sort(arr, p + 1, r);
    }

    private static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, 6, 8, 1, 4, 5, 7};
        new QuickSort2Ways().sort(a);
        SortHelper.print(a);
    }
}
