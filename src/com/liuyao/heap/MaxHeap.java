package com.liuyao.heap;

/**
 * Created By liuyao on 2018/4/18 20:38.
 */
public class MaxHeap<Item extends Comparable> {
    public Item[] data;
    public int count;
    public int capacity;

    public MaxHeap(int capacity) {
        data= (Item[]) new Comparable[capacity+1];
        count=0;
        this.capacity = capacity;
    }
    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count==0;
    }

    public void insert(Item item){
        assert count+1<=capacity;
        data[count+1]=item;
        count++;
        shifUp(count);
    }

    private void shifUp(int k){
        while (k>1 && data[k/2].compareTo(data[k])<0){
            swap(k,k/2);
            k/=2;
        }
    }

    private void swap(int i,int j){
        Item item=data[i];
        data[i]=data[j];
        data[j]=item;
    }

    public Item getMax(){
        assert count>0;
        return data[1];
    }

    public Item extractMax(){
        assert count>0;
        Item ret=data[1];

        swap(1,count);
        count--;
        shiftDown(1);
        return ret;
    }

    public void shiftDown(int k){
       while (2*k <= count){
           int j=2*k;
           //选择两个子树中较大的那个
           if (j+1 <=count && data[j+1].compareTo(data[j])>0){
               j++;
           }
//           如果当前节点比两个子树节点都打，则退出
           if (data[k].compareTo(data[j])>=0){
               break;
           }
           swap(k,j);
           k=j;
       }
    }
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap=new MaxHeap<>(100);
        int N=15;
        int M=100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(new Integer((int) (Math.random()*M)));
        }

        Integer[] arr=new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i]=maxHeap.extractMax();
            System.out.print(arr[i]+" ");
        }
    }
}
