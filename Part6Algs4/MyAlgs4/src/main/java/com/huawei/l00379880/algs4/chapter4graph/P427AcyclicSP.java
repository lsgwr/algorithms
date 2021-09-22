/***********************************************************
 * @Description : 基于拓扑排序的最短路径计算方法，更快更普适
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/24 上午12:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

public class P427AcyclicSP {
    /**
     * distTo[v]:起点s到v的最短距离
     */
    private double[] distTo;
    /**
     * edgeTo[v]:起点s到v的最短路径上的最后一条边
     */
    private P415DirectedEdge[] edgeTo;

    public P427AcyclicSP(P415EdgeWeightedDigraph digraph, int s) {
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

        // 按照拓扑排序的结果记性边的松弛
        P376Topological topological = new P376Topological(digraph);

        if (!topological.hasOrder()) {
            throw new IllegalArgumentException("Digraph is not acyclic");
        }
        for (int v : topological.getOrder()) {
            for (P415DirectedEdge edge : digraph.adj(v)) {
                relax(edge);
            }
        }
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
