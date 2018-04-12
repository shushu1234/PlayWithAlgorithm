package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

import java.util.Arrays;

/**
 * Created By liuyao on 2018/4/12 21:03.
 */
public class MergeBU {
    private MergeBU(){}

    private static void merge(Comparable[] arr,int l,int mid,int r){
        Comparable[] aux= Arrays.copyOfRange(arr,l,r+1);
        int i=l,j=mid+1;
        for (int k = l; k <=r ; k++) {
            if (i>mid){
                arr[k]=aux[j-1];
                j++;
            }else if (j>r){
                arr[k]=aux[i-l];i++;
            }else if (aux[i-l].compareTo(arr[j-l])<0){
                arr[k]=aux[i-l];
                i++;
            }else {
                arr[k]=aux[j-l];
                j++;
            }
        }
    }

    public static void sort(Comparable[] arr){
        int n=arr.length;

//        已经优化对于小数组，使用插入排序
        for (int i = 0; i < n; i+=16) {
            new Insertion().sort(arr,i,Math.min(i+15,n-1));
        }

        for (int sz = 16; sz < n; sz+=sz) {
            for (int i = 0; i < n-sz; i+=sz+sz) {
                if (arr[i+sz-1].compareTo(arr[i+sz])>0){
                    merge(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1));
                }
            }
        }

    }

//    Merge Sort BU是一个O(nlogn)的算法，虽然有两个for循环，但却可以在很短的时间内处理
//    大量的数据
    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new MergeBU().sort(a);
        SortHelper.print(a);
    }
}
