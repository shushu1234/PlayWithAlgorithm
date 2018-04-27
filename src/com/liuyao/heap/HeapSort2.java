package com.liuyao.heap;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/27 15:24.
 */
public class HeapSort2 {
    private HeapSort2(){}

    public void sort(Comparable[] arr){
        Heapify<Comparable> heapify=new Heapify<>(arr);

        int n=arr.length;
        for (int i = n-1; i >=0 ; i--) {
            arr[i]=heapify.extractMax();
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new HeapSort2().sort(a);
        SortHelper.print(a);
    }
}
