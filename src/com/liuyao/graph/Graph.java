package com.liuyao.graph;

/**
 * Created By liuyao on 2018/4/30 15:36.
 */
public interface Graph {
    public int V();

    public int E();

    public void addEdge(int v, int w);

    public boolean hasEdge(int v, int w);

    void show();

    public Iterable<Integer> adj(int v);
}
