package com.liuyao.graph2.lazyprim;

import com.liuyao.graph2.Edge;
import com.liuyao.graph2.ReadWeightedGraph;
import com.liuyao.graph2.SparseWeightedGraph;
import com.liuyao.graph2.WeightedGraph;

import java.util.Vector;

/**
 * Created By liuyao on 2018/5/1 18:23.
 */
public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph G;              // 图的引用
    private IndexMinHeap<Weight> ipq;     // 最小索引堆, 算法辅助数据结构
    private Edge<Weight>[] edgeTo;        // 访问的点所对应的边, 算法辅助数据结构
    private boolean[] marked;             // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;     // 最小生成树所包含的所有边
    private Number mstWeight;             // 最小生成树的权值

    // 构造函数, 使用Prim算法求图的最小生成树
    public PrimMST(WeightedGraph g) {
        assert (g.E() >= 1);
        G = g;
        ipq = new IndexMinHeap<>(G.V());

        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];

        // 算法初始化
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new Vector<Edge<Weight>>();

        //Prim
        visit(0);
        while (!ipq.isEmpty()) {
            // 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是点的索引, 通过点的索引找到相对应的边
            int v = ipq.extractMinIndex();
//            要确保当前到v的边不为空
            assert (edgeTo[v] != null);
//            将这条边添加到最短路径的边里面
            mst.add(edgeTo[v]);
//            继续查找v的周围的边
            visit(v);
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }

    }

    public void visit(int v) {
        assert !marked[v];
//        设置已被标记
        marked[v] = true;

        for (Object item : G.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) item;
//            w为边的另一个顶点
            int w = e.other(v);
//            检查该顶点是否已经被标记了
            if (!marked[w]) {
//                如果到改点的边为null
                if (edgeTo[w] == null) {
//                    则设置当前边到w
                    edgeTo[w] = e;
//                    插入索引堆，索引为w，值为边的权值
                    ipq.insert(w, e.wt());
                } else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {  //如果新的权值小于原来到w的边的权值
//                    更新w的边
                    edgeTo[w] = e;
//                    改变w的的权值
                    ipq.change(w, e.wt());
                }
            }
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

    // 测试 Prim
    public static void main(String[] args) {

        String filename = "testG1-2.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        Vector<Edge<Double>> mst = primMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }

}
