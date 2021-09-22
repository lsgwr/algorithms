/***********************************************************
 * @Description : 有向图(Directed Graph)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/19 下午8:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P98LinkedListBag;
import com.huawei.l00379880.mylib.file.In;

public class P366Digraph {
    /**
     * 换行符
     */
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * 顶点(vertex,复数为vertices)数目
     */
    private final int V;
    /**
     * 边的数目
     */
    private int E;
    /**
     * 邻接点列表(从当前节点指出的边的列表，列表的大小即为出度)
     */
    private P98LinkedListBag<Integer>[] adj;
    /**
     * 入度(指向该顶点的边的条数 )
     */
    private int[] indegree;

    /**
     * 利用边的数目来初始化一个图
     *
     * @param V 边的条数
     */
    public P366Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("顶点的数目V不能小于0！");
        }
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (P98LinkedListBag<Integer>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
    }

    public P366Digraph(In in) {
        this.V = in.readInt();
        if (V < 0) {
            throw new IllegalArgumentException("顶点的数目不能小于0");
        }
        indegree = new int[V];
        adj = (P98LinkedListBag<Integer>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边的数目不能小于0");
        }
        for (int i = 0; i < E; i++) {
            // 读入一条边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * 用一个现有的有向图来初始化自己
     *
     * @param digraph 已知的有向图
     */
    public P366Digraph(P366Digraph digraph) {
        this(digraph.getV());
        this.E = digraph.getE();
        for (int v = 0; v < V; v++) {
            // 获取指定顶点的入度
            this.indegree[v] = digraph.getIndegree(v);
        }
        for (int v = 0; v < digraph.getV(); v++) {
            P94LinkedListStack<Integer> reverseStack = new P94LinkedListStack<>();
            // 获取指点邻接点Bag
            for (int w : digraph.adj[v]) {
                reverseStack.push(w);
            }
            for (int w : reverseStack) {
                adj[v].add(w);
            }
        }
    }

    /**
     * 添加一条v指向w的有向边，同时w的入度+1
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        // 因为是从v-->w所以w的入度加1
        indegree[w]++;
        E++;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
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
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 返回反向图(每条边的方向都反一下)
     */
    public P366Digraph reverse() {
        P366Digraph reverseDigraph = new P366Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                // 每条边反向一下即可
                reverseDigraph.addEdge(w, v);
            }
        }
        return reverseDigraph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("共有" + V + "个顶点，" + E + "条边" + NEW_LINE);
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
