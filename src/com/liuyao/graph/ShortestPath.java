package com.liuyao.graph;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

/**
 * Created By liuyao on 2018/4/30 21:40.
 */
public class ShortestPath {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;
    private int[] ord;

    public ShortestPath(Graph g, int s) {
        G = g;
        assert s >= 0 && s < G.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        this.s = s;

        // 无向图最短路径算法, 从s开始广度优先遍历整张图
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.addLast(s);
        visited[s] = true;
        ord[s] = 0;
        while (!list.isEmpty()) {
            int v = list.removeFirst();
            for (int i : G.adj(v)) {
                if (!visited[i]) {
                    list.addLast(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + i;
                }
            }
        }

    }

    // 查询从s点到w点是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < G.V();
//        如果从s到w有路径则会返回返回true
        return visited[w];
    }

    // 查询从s点到w点的路径, 存放在vec中
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

    // 打印出从s点到w点的路径
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

    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1
    public int length(int w) {
        assert w >= 0 && w < G.V();
        return ord[w];
    }

    public static void main(String[] args) {
        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        ShortestPath bfs = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(6);
    }
}
