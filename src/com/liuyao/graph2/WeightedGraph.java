package com.liuyao.graph2;

/**
 * Created By liuyao on 2018/5/1 15:05.
 */
public interface WeightedGraph<Weight extends Number & Comparable> {
    public int V();

    public int E();

    public void addEdge(Edge<Weight> e);

    public boolean hasEdge(int v, int w);

    public void show();

    public Iterable<Edge<Weight>> adj(int v);
}
