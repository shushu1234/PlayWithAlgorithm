package com.liuyao.heap;

/**
 * Created By liuyao on 2018/4/18 20:38.
 */
public class Heapify<Item extends Comparable> {
    public Item[] data;
    public int count; //数组中的元素个数
    public int capacity;

    public Heapify(Item arr[]){
        int n=arr.length;
        data= (Item[]) new Comparable[n+1];
        capacity=n;

//        现将arr中的元素复制到data中
        for (int i = 0; i < n; i++) {
            data[i+1]=arr[i];
        }
        count=n;

//        从第一个非叶子节点开始Shift Down操作
        for (int i = count/2; i >= 1 ; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Item item) {
        assert count + 1 <= capacity;
        data[count + 1] = item;
        count++;
        shifUp(count);
    }

    private void shifUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void swap(int i, int j) {
        Item item = data[i];
        data[i] = data[j];
        data[j] = item;
    }

    public Item getMax() {
        assert count > 0;
        return data[1];
    }

    /**
     * 取出当前堆中的最大值
     * @return
     */
    public Item extractMax() {
        assert count > 0;
        Item ret = data[1];

        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    public void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            //选择两个子树中较大的那个
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
//           如果当前节点比两个子树节点都打，则退出
            if (data[k].compareTo(data[j]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

}
