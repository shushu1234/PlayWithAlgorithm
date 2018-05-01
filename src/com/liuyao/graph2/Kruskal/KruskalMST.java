package com.liuyao.graph2.Kruskal;

import com.liuyao.graph2.Edge;
import com.liuyao.graph2.ReadWeightedGraph;
import com.liuyao.graph2.SparseWeightedGraph;
import com.liuyao.graph2.WeightedGraph;

import java.util.Vector;

/**
 * Created By liuyao on 2018/5/1 21:17.
 */
public class KruskalMST<Weight extends Number & Comparable> {
    private Vector<Edge<Weight>> mst;
    private Number mstWeight;

    // 构造函数, 使用Kruskal算法计算graph的最小生成树
    public KruskalMST(WeightedGraph graph) {
        mst = new Vector<Edge<Weight>>();
//        将图中的所有边存放到一个最小堆中
        MinHeap<Edge<Weight>> pq = new MinHeap<Edge<Weight>>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object item : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
//                为了避免重复插入边，设置只有当e的前面点比后面小或等于时才插入
                if (e.v() <= e.w()) {
                    pq.insert(e);
                }
            }
        }
//        创建一个大小为点的个数的并查集，查看所有点的是否在同一个并查集中
        UnionFind unionFind = new UnionFind(graph.V());
//        当pq不为空或者边数已经为V-1时就停止
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
//            取出最小边
            Edge<Weight> e = pq.extractMin();
//            如果当前最小的边的两个顶点在同一个集合里面,继续查找
            if (unionFind.isConnected(e.v(), e.w())) {
                continue;
            }
//            否者将当前边加入到最小路径中
            mst.add(e);
//            将边的两个顶点合并
            unionFind.unionElements(e.v(), e.w());
        }

        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }

    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

    // 测试 Kruskal
    public static void main(String[] args) {

        String filename = "testG1-2.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}
