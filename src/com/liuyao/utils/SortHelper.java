package com.liuyao.utils;

/**
 * Created By liuyao on 2018/4/10 19:55.
 */
public class SortHelper {
    public static void swap(Object[] arr,int i,int j){
        Object temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void print(Object[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
