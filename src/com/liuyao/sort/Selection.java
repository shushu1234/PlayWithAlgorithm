package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/10 19:52.
 */
public class Selection {

    public void sort(Comparable[] arr){
        int n=arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex=i;
            for (int j = i+1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex])<0){
                    minIndex=j;
                }
            }
            SortHelper.swap(arr,i,minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new Selection().sort(a);
        SortHelper.print(a);
    }

}
