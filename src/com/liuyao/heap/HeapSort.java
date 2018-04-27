package com.liuyao.heap;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/27 15:49.
 */
public class HeapSort {
    private HeapSort(){}

    public void sort(Comparable[] arr){
        int n=arr.length;

        // 注意，此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引=n-1
        for (int i = (n-1-1)/2; i >=0 ; i--) {
            shiftDown2(arr,n,i);
        }

        for (int i = n-1; i >0 ; i--) {
            swap(arr,0,i);
            shiftDown2(arr,i,0);
        }
    }

    // 交换堆中索引为i和j的两个元素
    private static void swap(Object[] arr, int i, int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 优化的shiftdown过程，使用赋值的方式取代不断的swap ,注释的语句
    // 该优化思想和我们之前对插入排序进行优化的思路是一致的。
    public void shiftDown2(Comparable[] arr,int n,int k){
//        Comparable e=arr[k];

        while (2*k+1<n){
            int j=2*k+1;
            if (j+1<n && arr[j+1].compareTo(arr[j])>0){
                j+=1;
            }
            if (arr[k].compareTo(arr[j])>0){
                break;
            }
           swap(arr,k,j);
//            arr[k]=arr[j];
            k=j;
        }
//        arr[k]=e;
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new HeapSort().sort(a);
        SortHelper.print(a);
    }
}

