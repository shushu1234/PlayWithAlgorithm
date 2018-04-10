package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

/**
 * Created By liuyao on 2018/4/10 20:41.
 */
public class ShellSort {
    public void sort(Comparable[] arr){
        int n=arr.length;
        int h=1;
        while (h <n/3){
            h=3*h+1;
        }
        while (h>=1){
//            间隔为h使用插入排序
            for (int i = h; i <n ; i++) {
                Comparable e=arr[i];
                int j=i;
                for(;j>=h && arr[j-h].compareTo(e)>0;j-=h){
                    arr[j]=arr[j-h];
                }
                arr[j]=e;
            }
            h/=3;
        }
    }

    public static void main(String[] args) {
        Integer[] a={2,3,6,8,1,4,5,7};
        new ShellSort().sort(a);
        SortHelper.print(a);
    }
}
