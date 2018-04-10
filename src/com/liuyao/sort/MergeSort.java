package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

import java.util.Arrays;

/**
 * Created By liuyao on 2018/4/10 20:48.
 */
public class MergeSort {
    public void sort(Comparable[] arr){
        sort(arr,0,arr.length-1);
    }

    public void sort(Comparable[] arr,int l,int r){
//        if (l-r<=5){
//            new Insertion().sort(arr,l,r);
//        }
        if (l >= r){
            return;
        }
        int mid=(l+r)/2;

        sort(arr,l,mid);
        sort(arr,mid+1,r);
        if (l < mid)
            merge(arr,l,mid,r);
    }

    public void merge(Comparable[] arr,int l,int mid,int r){
        int i=l;
        int j=mid+1;
        Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);

        for (int k = l; k <=r ; k++) {
            if (i >mid){
                arr[k]=aux[j-l];
                j++;
            }else if (j > r){
                arr[k]=aux[i-l];
                i++;
            }else if (aux[i-l].compareTo(aux[j-l])<0){
                arr[k]=aux[i-l];
                i++;
            }else {
                arr[k]=aux[j-l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new MergeSort().sort(a);
        SortHelper.print(a);
    }
}
