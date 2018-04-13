package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/13 22:09.
 */
public class QuickSort {
    private QuickSort(){}

    private static int partition(Comparable[] arr,int l,int r){
       Comparable v=arr[l];
       int j=l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i].compareTo(v)<0){
                j++;
                swap(arr,j,i);
            }
        }
        swap(arr,l,j);
        return j;
    }

    private static void sort(Comparable[] arr,int l,int r){
        if (l>=r){
            return;
        }
        int p=partition(arr,l,r);
        sort(arr,1,p-1);
        sort(arr,p+1,r);
    }

    public static void sort(Comparable[] arr){
        int n=arr.length;
        sort(arr,0,n-1);
    }

    public static void swap(Object[] arr,int i,int j){
        Object temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new QuickSort().sort(a);
        SortHelper.print(a);
    }
}
