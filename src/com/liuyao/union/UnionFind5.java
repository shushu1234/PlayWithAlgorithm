package com.liuyao.union;

/**
 * Created By liuyao on 2018/4/29 17:43.
 */
public class UnionFind5 {
    private int[] parent; //parentb[i]表示第一个元素所指向的父节点
    private int count; //数据个数
    private int[] rank; //当前根下的元素个数

    public UnionFind5(int count) {
        parent = new int[count];
        rank = new int[count];
        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p) {
        assert (p >= 0 && p < count);
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
//        while (p != parent[p]) {
//            parent[p] = parent[parent[p]];
//            p = parent[p];
//        }
//        return p;

        //递归算法
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1; //qRoot作为根，高度加一
        }
    }

    public static void main(String[] args) {
        int n = 100000;

        UnionFindTestHelper.testUF4(n);
    }
}