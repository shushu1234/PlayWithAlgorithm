package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/10 20:01.
 */
public class Insertion {
    public void sort(Comparable[] arr){
        int n=arr.length;
        for (int i = 0; i < n; i++) {
//            写法一
//            for (int j = i; j >0 ; j--) {
//                if (arr[j].compareTo(arr[j-1])<0){
//                    SortHelper.swap(arr,j,j-1);
//                }else {
//                    break;
//                }
//            }

//            写法二
//            for (int j = i; j >0 && arr[j].compareTo(arr[j-1])<0 ; j--) {
//                SortHelper.swap(arr,j,j-1);
//            }

//            写法三
            Comparable e=arr[i];
            int j = i;
            for (; j >0 && arr[j-1].compareTo(e)>0; j--) {
                arr[j]=arr[j-1];
            }
            arr[j]=e;
        }
    }

    public void sort(Comparable[] arr,int l,int r){
        for (int i = l; i <=r ; i++) {
            Comparable e=arr[i];
            int j=i;
            for(;j>l && arr[j-1].compareTo(e)>0;j--){
                arr[j]=arr[j-1];
            }
            arr[j]=e;
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new Insertion().sort(a);
        SortHelper.print(a);
    }
}
