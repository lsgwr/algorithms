/***********************************************************
 * @Description : 无向加权图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/20 下午12:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P98LinkedListBag;
import com.huawei.l00379880.mylib.file.In;
import com.huawei.l00379880.mylib.math.StdRandom;

public class P395EdgeWeightedGraph {
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * 顶点的数目
     */
    private final int V;
    /**
     * 边的数目
     */
    private int E;
    /**
     * 邻接边列表
     */
    private P98LinkedListBag<P394Edge>[] adj;

    /**
     * 利用顶点数目来初始化一个无向加权图
     */
    public P395EdgeWeightedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("顶点的数目V不能小于0");
        }
        this.V = V;
        this.E = 0;
        adj = (P98LinkedListBag<P394Edge>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
    }

    public P395EdgeWeightedGraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("边的数目E不能小于0");
        }
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(E);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            P394Edge edge = new P394Edge(v, w, weight);
            addEdge(edge);
        }
    }

    /**
     * 利用输入流来初始化
     */
    public P395EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边的数目E不能小于0");
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            P394Edge edge = new P394Edge(v, w, weight);
            addEdge(edge);
        }
    }

    /**
     * 利用另一个无向有权图来初始化
     */
    public P395EdgeWeightedGraph(P395EdgeWeightedGraph graph) {
        this(graph.getV());
        this.E = graph.getE();
        for (int v = 0; v < graph.getV(); v++) {
            P94LinkedListStack<P394Edge> reverseStack = new P94LinkedListStack<>();
            for (P394Edge edge : graph.adj[v]) {
                reverseStack.push(edge);
            }
            for (P394Edge edge : reverseStack) {
                adj[v].add(edge);
            }
        }
    }

    /**
     * 校验顶点是否合法
     *
     * @param v 指定顶点
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

    /**
     * 添加一条最小生成树的边
     */
    private void addEdge(P394Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    /**
     * 获取指定节点的临边
     */
    public Iterable<P394Edge> getAdj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 指定节点的度(从该节点指出的边的数目,即邻接点的数目)
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 获取所有的边
     */
    public Iterable<P394Edge> edges() {
        P98LinkedListBag<P394Edge> edgeList = new P98LinkedListBag<>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (P394Edge edge : getAdj(v)) {
                if (edge.other(v) > v) {
                    edgeList.add(edge);
                } else if (edge.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        edgeList.add(edge);
                    }
                    selfLoops++;
                }
            }
        }
        return edgeList;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " " + E + NEW_LINE);
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (P394Edge edge : getAdj(v)) {
                sb.append(edge + " ");
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
