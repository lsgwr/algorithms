/***********************************************************
 * @Description : 加权有向图的基本数据类型
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/23 上午12:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P98LinkedListBag;
import com.huawei.l00379880.mylib.file.In;
import com.huawei.l00379880.mylib.math.StdRandom;

public class P415EdgeWeightedDigraph {
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
     * 邻接边列表(从当前节点指出的边的列表，列表的大小即为出度)
     */
    private P98LinkedListBag<P415DirectedEdge>[] adj;
    /**
     * indegree[v]:v节点的入度，即从外面指向v的边的数目
     */
    private int[] indegree;

    public P415EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("顶点的数目V不能小于0");
        }
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (P98LinkedListBag<P415DirectedEdge>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
    }

    public P415EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("边的数目E不能小于0");
        }
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(E);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            P415DirectedEdge edge = new P415DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public P415EdgeWeightedDigraph(In in) {
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
            P415DirectedEdge edge = new P415DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public P415EdgeWeightedDigraph(P415EdgeWeightedDigraph digraph) {
        this(digraph.getV());
        this.E = digraph.getE();
        for (int v = 0; v < digraph.getV(); v++) {
            this.indegree[v] = digraph.getIndegree(v);
        }
        for (int v = 0; v < digraph.getV(); v++) {
            P94LinkedListStack<P415DirectedEdge> reverseStack = new P94LinkedListStack<>();
            for (P415DirectedEdge edge : digraph.adj[v]) {
                reverseStack.push(edge);
            }
            for (P415DirectedEdge edge : reverseStack) {
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

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    /**
     * 获得入度(从外面指向当前节点的边)
     */
    public int getIndegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * 获得出度(出度指从当前点指向外面的边的数目，在有向图中恰好指的是邻接点的数目)
     */
    public int getOutdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 获取指定节点的邻接点的Bag
     *
     * @param v 指定的节点
     * @return 在此处是一个Bag
     */
    public Iterable<P415DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 添加一条v指向w的有向边，同时w的入度+1
     */
    public void addEdge(P415DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(edge);
        // 因为是从v-->w所以w的入度加1
        indegree[w]++;
        E++;
    }

    /**
     * 获取所有边的Bag
     *
     * @return
     */
    public Iterable<P415DirectedEdge> edges() {
        P98LinkedListBag<P415DirectedEdge> edgeBag = new P98LinkedListBag<>();
        for (int v = 0; v < V; v++) {
            for (P415DirectedEdge edge : adj[v]) {
                edgeBag.add(edge);
            }
        }
        return edgeBag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("共有" + V + "个顶点，" + E + "条边" + NEW_LINE);
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (P415DirectedEdge edge : adj[v]) {
                sb.append(edge + " ");
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
