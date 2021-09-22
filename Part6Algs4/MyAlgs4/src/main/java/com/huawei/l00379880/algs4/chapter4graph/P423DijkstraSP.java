/***********************************************************
 * @Description : DijkstraSP,加权有向图中最短路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/23 上午1:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter2sort.P203IndexMinPQ;

public class P423DijkstraSP {
    /**
     * distTo[v]:起点s到v的最短距离
     */
    private double[] distTo;
    /**
     * edgeTo[v]:起点s到v的最短路径上的最后一条边
     */
    private P415DirectedEdge[] edgeTo;
    /**
     * 用于存储顶点的最小优先队列
     */
    private P203IndexMinPQ<Double> pq;

    /**
     * 计算最短距离树的起点
     *
     * @param digraph 待计算地有向加权图
     * @param s       起点
     */
    public P423DijkstraSP(P415EdgeWeightedDigraph digraph, int s) {
        for (P415DirectedEdge edge : digraph.edges()) {
            if (edge.getWeight() < 0) {
                throw new IllegalArgumentException("边" + edge + "的权重为负数");
            }
        }
        int V = digraph.getV();
        distTo = new double[V];
        edgeTo = new P415DirectedEdge[V];
        validateVertex(s);
        // 初始化每个点到起点的最短距离。初始化为正无穷大
        for (int v = 0; v < V; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        // 起点到起点的距离为0
        distTo[s] = 0.0;
        // 按照起点到当前点的距离松弛边
        pq = new P203IndexMinPQ<>(V);
        // 先插入第一个点
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            // 遍历邻接边即可
            for (P415DirectedEdge edge : digraph.adj(v)) {
                relax(edge);
            }
        }

        assert check(digraph, s);
    }

    private boolean check(P415EdgeWeightedDigraph digraph, int s) {
        // 1.检查边的权重是否为非负
        for (P415DirectedEdge edge : digraph.edges()) {
            if (edge.getWeight() < 0) {
                System.err.println("存在权重为负数的边：" + edge);
            }
        }

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
        return true;
    }

    /**
     * 通过松弛邻接边，如果最短距离变化就更新pq
     */
    private void relax(P415DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        if (distTo[w] > distTo[v] + edge.getWeight()) {
            distTo[w] = distTo[v] + edge.getWeight();
            edgeTo[w] = edge;
            if (pq.contains(w)) {
                // 有的话就更新
                pq.decreaseKey(w, distTo[w]);
            } else {
                // 没有的话就插入
                pq.insert(w, distTo[w]);
            }
        }
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

}
