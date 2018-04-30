package com.liuyao.graph;

/**
 * Created By liuyao on 2018/4/30 16:33.
 */
// 求无权图的联通分量
public class Components {
    Graph G;   //图的引用
    private boolean[] visited;  //记录dfs的过程中节点是否被访问过
    private int ccount;  //记录联通分量个数
    private int[] id;  //每个节点所对应的联通分量标记

    public Components(Graph graph) {
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];

        //初始化联通非分量个数为0个
        ccount = 0;

        for (int i = 0; i < G.V(); i++) {
            //初始化每个节点的都没被访问过
            visited[i] = false;

            //初始化每个节点的联通分量标记为-1
            id[i] = -1;
        }

        //求图的联通分量
        for (int i = 0; i < G.V(); i++) {
            //如果当前节点没有被遍历
            if (!visited[i]) {
                //执行深度优先搜索
                dfs(i);

                //设置当前的联通分量个数
                ccount++;
            }
        }
    }

    //    图的深度优先遍历
    private void dfs(int v) {
        //设置当前节点已经被访问
        visited[v] = true;

        //标记当前节点所属的联通分量
        id[v] = ccount;

        //迭代遍历
        for (int i : G.adj(v)) {
            if (!visited[i]) {
                //递归查询
                dfs(i);
            }
        }
    }

    // 返回图的联通分量个数
    int count() {
        return ccount;
    }

    // 查询两个节点是否联通
    public boolean isConnected(int v, int w) {
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        //比较两个节点的联通分量是否相同
        return id[v] == id[w];
    }

    public static void main(String[] args) {
        // TestG1.txt
        String filename1 = "testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
    }
}
