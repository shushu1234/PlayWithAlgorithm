package com.liuyao.heap;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/27 14:54.
 */
public class HeapSort1 {
    private HeapSort1(){}

    /**
     * 逐个的将元素插入进堆中，并逐个的进行Shift Up操作进行排序
     * 然后再依次的将元素取出来。无论是创建的过程还是取出的过程，时间复杂度都是O(nlogn)
     * @param arr
     */
    public void sort(Comparable[] arr){
        int n=arr.length;
        MaxHeap<Comparable> maxHeap=new MaxHeap<>(n);
        for (int i = 0; i < n; i++) {
            maxHeap.insert(arr[i]);
        }
        for (int i = n-1; i >=0 ; i--) {
            arr[i]=maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new HeapSort1().sort(a);
        SortHelper.print(a);
    }
}
