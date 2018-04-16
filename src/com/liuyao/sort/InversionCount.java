package com.liuyao.sort;

import com.liuyao.utils.SortHelper;

import java.util.Arrays;

/**
 * Created By liuyao on 2018/4/15 21:18.
 */
public class InversionCount {
    private InversionCount(){}

    private static long merge(Comparable[] arr,int l,int mid,int r){
        Comparable[] aux= Arrays.copyOfRange(arr,l,r+1);

//        初始化逆序对数个数为0L
        long res=0L;

//        初始化时，i指向左半边起始索引位置l，j指向右半边起始索引位置mid+1
        int i=l,j=mid+1;

        for (int k = l; k <=r ; k++) {

//            左半边已经全部处理完
            if (i >mid){
                arr[k]=aux[j-l];
                j++;
            } else if (j >r){  //右半边已经全部处理完
                arr[k]=aux[i-l];
                i++;
            } else if (aux[i-l].compareTo(aux[j-l])<=0){ //左边元素比右边小，将左边元素放入
                arr[k]=aux[i-l];
                i++;
            }else {   //否者右边的元素比左边的小，存在逆序对
                arr[k]=aux[j-l];
                j++;
                //此时这个元素和左半边部分的所有为处理的元素构成逆序对
                // 左半边部分此时为处理的元素个数为mid-j+l
                res+=(mid-i+l);
            }
        }
        return res;
    }

    private static long solve(Comparable[] arr,int l,int r){
        if (l>=r){
            return 0L;
        }
        int mid=l+(r-l)/2;
// 求出 arr[l...mid] 范围的逆序数
        long res1=solve(arr,l,mid);
// 求出 arr[mid+1...r] 范围的逆序数
        long res2=solve(arr,mid+1,r);

        return res1+res2+merge(arr,l,mid,r);
    }

    public static long solve(Comparable[] arr){
        return solve(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
        Integer[] a = {2, 3, 6, 8, 1, 4, 5, 7};
        System.out.println(new InversionCount().solve(a));
    }
}
