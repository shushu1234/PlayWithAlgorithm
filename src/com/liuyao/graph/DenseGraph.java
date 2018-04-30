package com.liuyao.graph;

import java.util.Vector;

/**
 * Created By liuyao on 2018/4/30 11:27.
 */
//稠密图 - 邻接矩阵
public class DenseGraph implements Graph {
    private int n; // 节点数
    private int m; // 边数
    private boolean directed; // 是否为有向图
    private boolean[][] g;    // 图的具体数据

    public DenseGraph(int n, boolean directed) {
        assert n > 0;
        this.n = n;
        this.m = 0;  //初始化的时候没有任何边
        this.directed = directed;
        // g初始化为n*n的布尔矩阵, 每一个g[i][j]均为false, 表示没有任和边
        // false为boolean型变量的默认值
        g = new boolean[n][n];
    }

    // 返回节点个数
    @Override
    public int V() {
        return n;
    }

    // 返回边的个数
    @Override
    public int E() {
        return m;
    }

    // 验证图中是否有从v到w的边
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w];
    }


    // 向图中添加一个边
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        if (hasEdge(v, w)) {
            return;
        }
        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
        m++;
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        Vector<Integer> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }

    // 显示邻接矩阵构造的图
    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((g[i][j] ? 1 : 0) + "\t");
            }
            System.out.println();
        }
    }
}
