package com.liuyao.graph;

import java.util.Stack;
import java.util.Vector;

/**
 * Created By liuyao on 2018/4/30 17:33.
 */
public class Path {
    private Graph G;  //图的引用
    private int s;  //起始点
    private boolean[] visited;  //记录dfs的过程中节点是否被访问
    private int[] from; //记录路径，from[i]表示查找的路径上i的上一个节点

    // 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
    public Path(Graph graph, int s) {
        G = graph;
        assert s >= 0 && s < G.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1; // 初始化时全部初始化为-1
        }
        this.s = s;

//        寻路算法 根据起点s开始，只深搜一次
        dfs(s);
    }

    // 图的深度优先遍历
    private void dfs(int v) {
        //设置当前节点已被访问
        visited[v] = true;
        for (int i : G.adj(v)) {
            if (!visited[i]) {
//                设置当前节点由上一个节点v来的
                from[i] = v;
                dfs(i);
            }
        }
    }

    // 查询从s点到w点是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < G.V();
//        如果从s到w有路径则会返回返回true
        return visited[w];
    }

    Vector<Integer> path(int w) {
        assert hasPath(w);
        Stack<Integer> stack = new Stack<Integer>();

        int p = w;
//        其实节点的from标记为-1
        while (p != -1) {
//            依次将p的前面节点压栈
            stack.push(p);
            p = from[p];
        }

//        从尾到头依次构建路径
        Vector<Integer> res = new Vector<Integer>();
        while (!stack.empty()) {
            res.add(stack.pop());
        }
//        返回最后的路径
        return res;
    }

    public void showPath(int w) {
        assert hasPath(w);

        Vector<Integer> vec = path(w);
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.elementAt(i));
//            最后一个直接输出换行
            if (i == vec.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }

    // 测试寻路算法
    public static void main(String[] args) {

        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g, 0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
    }
}
