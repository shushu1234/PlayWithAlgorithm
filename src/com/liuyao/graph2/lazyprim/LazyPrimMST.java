package com.liuyao.graph2.lazyprim;

import com.liuyao.graph2.Edge;
import com.liuyao.graph2.ReadWeightedGraph;
import com.liuyao.graph2.SparseWeightedGraph;
import com.liuyao.graph2.WeightedGraph;

import java.util.Vector;

/**
 * Created By liuyao on 2018/5/1 17:02.
 */
public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> G;    // 图的引用
    private MinHeap<Edge<Weight>> pq;   // 最小堆, 算法辅助数据结构
    private boolean[] marked;           // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;   // 最小生成树所包含的所有边
    private Number mstWeight;           // 最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> g) {
        G = g;
//        构造最小堆，个数为点的个数
        pq = new MinHeap<Edge<Weight>>(G.E());
//        设置每条边都没被访问过
        marked = new boolean[G.V()];
//        申请一个Vector来存放最短路径的边
        mst = new Vector<Edge<Weight>>();

//        从0开始访问
        visit(0);
//        当最小堆不为空的，继续
        while (!pq.isEmpty()) {
//            取得当前最小堆中最小的那条边
            Edge<Weight> e = pq.extractMin();
//            如果边的两个点的访问标记一样，说明在同一个切分中，继续找最小的
            if (marked[e.v()] == marked[e.w()]) {
                continue;
            }
//            否者加入到mst中
            mst.add(e);
//            看边的哪一个点没有被访问，就visit把它周围的边加入到最小堆中
            if (!marked[e.v()]) {
                visit(e.v());
            } else {
                visit(e.w());
            }
        }

//        最后求所有最短路径的权值总和
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();

        }
    }

    // 访问节点v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;
        // 将和节点v相连接的所有未访问的边放入最小堆中
        for (Edge<Weight> e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    //    返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    //    返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

    public static void main(String[] args) {
        String filename = "testG1-2.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
    }
}
