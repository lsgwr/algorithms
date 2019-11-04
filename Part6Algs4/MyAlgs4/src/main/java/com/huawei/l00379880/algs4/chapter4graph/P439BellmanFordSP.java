/***********************************************************
 * @Description : 解决加权有向图的最准确而且有效的方法
 *                兼容负数边并能实时监测环
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/24 上午12:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

public class P439BellmanFordSP {
    /**
     * distTo[v]:起点s到点v的最短距离
     */
    private double[] distTo;
    /**
     * edgeTo[v]:起点s到点v的最短路径上的最后一条边
     */
    private P415DirectedEdge[] edgeTo;
    /**
     * onQueue[v]:顶点v是否在待放松点的队列里
     */
    private boolean[] onQueue;
    /**
     * 存放待放松顶点的队列
     */
    private P95LinkedListQueue<Integer> queue;
    /**
     * 调用relax()函数的次数
     */
    private int cost;
    /**
     * negative cycle (or null if no such cycle)
     */
    private Iterable<P415DirectedEdge> cycle;

    public P439BellmanFordSP(P415EdgeWeightedDigraph digraph, int s) {
        int V = digraph.getV();
        distTo = new double[V];
        edgeTo = new P415DirectedEdge[V];
        onQueue = new boolean[V];
        for (int v = 0; v < V; v++) {
            // 求最小距离应该初始化为最大值
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // Bellman-Ford核心算法
        queue = new P95LinkedListQueue<>();
        queue.enqueue(s);
        onQueue[s] = true;
        // 队列为非空并且没负权重的换环
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(digraph, v);
        }
        assert check(digraph, s);
    }

    /**
     * relax vertex v and put other endpoints on queue if changed
     */
    private void relax(P415EdgeWeightedDigraph digraph, int v) {
        for (P415DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.getWeight()) {
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                if (!onQueue[w]) {
                    // 不在松弛点队列里就加进去
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % digraph.getV() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    // 发现了负权重环就返回
                    return;
                }
            }
        }
    }

    /**
     * by finding a cycle in predecessor graph
     */
    private void findNegativeCycle() {
        int V = edgeTo.length;
        P415EdgeWeightedDigraph digraph = new P415EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                digraph.addEdge(edgeTo[v]);
            }
        }
        // 环的检测
        P416EdgeWeightedDirectedCycle cycleFinder = new P416EdgeWeightedDirectedCycle(digraph);
        cycle = cycleFinder.getCycleStack();
    }

    /**
     * Is there a negative cycle reachable from the source vertex s?
     * 从顶点s是否能够到达一个负数环
     *
     * @return
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<P415DirectedEdge> getNegativeCycle() {
        return cycle;
    }

    /**
     * 校验顶点是否合法
     *
     * @param v 指定顶点
     */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

    /**
     * 返回起点s到顶点v的最短路径的长度
     */
    public double distTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle()) {
            throw new UnsupportedOperationException("Negative cost cycle exists");
        }
        return distTo[v];
    }

    /**
     * 起点s到顶点v之间是否存在一条路径
     * 因为初始化的路径都是无穷大，所以只要不是无穷大就是存在路径地
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 获得从起点s到顶点v的最短路径的可迭代路径对象集合
     */
    public Iterable<P415DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle()) {
            throw new UnsupportedOperationException("Negative cost cycle exists");
        }
        if (!hasPathTo(v)) {
            return null;
        }
        // 存储路径的栈
        P94LinkedListStack<P415DirectedEdge> path = new P94LinkedListStack<>();
        for (P415DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }
        return path;
    }

    /**
     * check optimality conditions: either
     * (i) there exists a negative cycle reacheable from s
     * or
     * (ii)  for all edges e = v->w:            distTo[w] <= distTo[v] + e.weight()
     * (ii') for all edges e = v->w on the SPT: distTo[w] == distTo[v] + e.weight()
     */
    private boolean check(P415EdgeWeightedDigraph digraph, int s) {
        // 1.检查是否有非负边
        if (hasNegativeCycle()) {
            double weight = 0.0;
            for (P415DirectedEdge edge : getNegativeCycle()) {
                weight += edge.getWeight();
            }
            if (weight >= 0.0) {
                System.err.println("error: weight of negative cycle = " + weight);
                return false;
            }
        } else {
            // no negative cycle reachable from source
            // 2.检查distTo[v]和edgeTo[v]是连续地
            if (distTo[s] != 0.0 || edgeTo[s] != null) {
                System.err.println("distTo[s]和edgeTo[s]是不连续地");
                return false;
            }

            int V = digraph.getV();
            for (int v = 0; v < V; v++) {
                if (v == s) {
                    continue;
                }
                if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[" + v + "] and edgeTo[" + v + "]不连续");
                    return false;
                }
            }
            // 3.检查是否所有的边edge = v-->w均满足distTo[w] <= distTo[v] + edge.getWeight()

            for (int v = 0; v < V; v++) {
                for (P415DirectedEdge edge : digraph.adj(v)) {
                    // 获取边的另一个点
                    int w = edge.to();
                    if (distTo[v] + edge.getWeight() < distTo[w]) {
                        System.err.println("edge " + edge + " not relaxed");
                        return false;
                    }
                }
            }
            // 4.check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
            for (int w = 0; w < V; w++) {
                if (edgeTo[w] == null) {
                    continue;
                }
                P415DirectedEdge edge = edgeTo[w];
                int v = edge.from();
                if (w != edge.to()) {
                    return false;
                }
                if (distTo[v] + edge.getWeight() != distTo[w]) {
                    System.err.println("edge " + edge + " on shortest path not tight!");
                    return false;
                }
            }
        }
        return true;
    }

}
