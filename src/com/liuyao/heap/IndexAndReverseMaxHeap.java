package com.liuyao.heap;

/**
 * Created By liuyao on 2018/4/18 20:38.
 */
public class IndexAndReverseMaxHeap<Item extends Comparable> {
    public Item[] data;  //最大索引堆中的数据
    public int[] indexes; //最大索引堆中的索引
    public int[] reverse; //最大索引堆中的反向索引
    public int count; //数组中的元素个数
    public int capacity;

    // 构造函数, 构造一个空堆, 可容纳capacity个元素
    public IndexAndReverseMaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1]; //索引是从1开始的
        indexes = new int[capacity + 1];
        reverse=new int[capacity+1];
        for (int i = 0; i <=capacity; i++) {
            reverse[i]=0;
        }
        count = 0;
        this.capacity = capacity;
    }

    // 返回索引堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值, 表示索引堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向最大索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
    // 传入的i对用户而言,是从0索引的
    public void insert(int i, Item item) {
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;

        i+=1;
        data[i] = item;
        indexes[count+1]=i;
        reverse[i]=count+1;
        count++;
        shifUp(count);
    }

    // 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
    private void shifUp(int k) {
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
            swapIndexes(k, k / 2);
            k /= 2;
        }
    }

    private void swap(int i, int j) {
        Item item = data[i];
        data[i] = data[j];
        data[j] = item;
    }

    // 交换索引堆中的索引i和j
    private void swapIndexes(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
        reverse[indexes[i]]=i;
        reverse[indexes[j]]=j;
    }

    // 获取最大索引堆中的堆顶元素,不删除
    public Item getMax() {
        assert count > 0;
        return data[indexes[1]];
    }

    // 获取最大索引堆中的堆顶元素的索引，不删除
    public int getMaxIndex(){
        assert count>0;
        return indexes[1]-1;
    }

    public Item getItem(int i){
        assert (contain(i));
        assert i+1 >=1 && i+1 <=capacity;
        return data[i+1];
    }

    // 从最大索引堆中取出堆顶元素, 即索引堆中所存储的最大数据，删除元素
    public Item extractMax() {
        assert count > 0;
        Item ret = data[indexes[1]];

        swapIndexes(1, count);
        reverse[indexes[count]]=0;
        count--;
        shiftDown(1);
        return ret;
    }

    // 从最大索引堆中取出堆顶元素的索引,删除
    public int extractMaxIndex(){
        assert count>0;
        int ret=indexes[1]-1;
        swapIndexes(1,count);
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);
        return ret;
    }

    // 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
    public void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            //选择两个子树中较大的那个
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j++;
            }
//           如果当前节点比两个子树节点都打，则退出
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) {
                break;
            }
            swapIndexes(k, j);
            k = j;
        }
    }

    public boolean contain(int i){
        return reverse[i+1]!=0;
    }

    public void change(int i,Item newItem){
        assert (contain(i));
        i+=1;
        data[i]=newItem;
        // 找到indexes[j] = i, j表示data[i]在堆中的位置
        // 之后shiftUp(j), 再shiftDown(j)
//        for (int j = 1; j <=count ; j++) {
//            if (indexes[j]==i){
//                shifUp(j);
//                shiftDown(j);
//                return;
//            }
//        }

        shifUp(reverse[i]);
        shiftDown(reverse[i]);
    }

    public static void main(String[] args) {
        IndexAndReverseMaxHeap<Integer> maxHeap = new IndexAndReverseMaxHeap<>(100);
        int N = 15;
        int M = 100;
        for (int i = 0; i < N; i++) {
            maxHeap.insert(i,new Integer((int) (Math.random() * M)));
        }

        Integer[] arr = new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
    }
}
