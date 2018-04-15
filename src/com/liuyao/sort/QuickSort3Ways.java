package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/15 20:20.
 */
public class QuickSort3Ways {
    private QuickSort3Ways() {
    }

    public static void sort(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            new Insertion().sort(arr, l, r);
            return;
        }
        swap(arr, l, (int) ((Math.random() * (r - l + 1)) + l));

        Comparable v = arr[l];
        int lt = l;   //arr[l+1...lt]<v
        int gt = r + 1; //arr[gt...r]>v
        int i = l + 1;  //arr[lt+1...i) == v

        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
            swap(arr, l, lt);
            sort(arr, l, lt - 1);
            sort(arr, gt, r);
        }

    }

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, 6, 8, 1, 4, 5, 7};
        new QuickSort3Ways().sort(a);
        SortHelper.print(a);
    }
}
