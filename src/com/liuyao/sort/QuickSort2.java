package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/15 18:13.
 */
public class QuickSort2 {
    private QuickSort2(){

    }

    private static int partition(Comparable[] arr,int l,int r){
//        随机在arr[l...r]的范围中，选择一个数值作为标定点
        swap(arr,l, (int) ((Math.random()*(r-l+1))+l));
        Comparable v=arr[l];
        int j=l;
        for (int i = l+1; i <=r ; i++) {
            if (arr[i].compareTo(v)<0){
                j++;
                swap(arr,j,i);
            }
        }
        swap(arr,l,j);
        return j;
    }

    public static void sort(Comparable[] arr,int l,int r){
//        对于小规模数组，采用插入排序
//        if (r-l<=15){
//            new Insertion().sort(arr,l,r);
//        }

        if (l>=r){
            return;
        }
        int p=partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }

    private static void sort(Comparable[] arr){
        sort(arr,0,arr.length-1);
    }

    private static void swap(Object[] arr,int i,int j){
        Object t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new QuickSort2().sort(a);
        SortHelper.print(a);
    }
}
