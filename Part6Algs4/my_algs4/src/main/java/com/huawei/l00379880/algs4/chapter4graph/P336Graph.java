/***********************************************************
 * @Description : 基于邻接表的图实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/14 上午1:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P98LinkedListBag;
import com.huawei.l00379880.mylib.file.In;

public class P336Graph {
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * 顶点数(vertex:顶点)
     */
    private final int V;
    /**
     * 边数，edge:边
     */
    private int E;
    /**
     * 顶点的邻接表数组(用Bag来实现)，几个顶点(V)这个adj的大小就是几
     */
    private P98LinkedListBag<Integer>[] adj;

    /**
     * 初始化一个V个定点、0条边的图
     *
     * @param V 顶点数目
     */
    public P336Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("顶点(Vertex)数目不能小于0");
        }
        this.V = V;
        this.E = 0;
        // 泛型数组的初始化,只能先声明泛型然后进行强制转换.Java本身决定地
        adj = (P98LinkedListBag<Integer>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
    }

    public P336Graph(In in) {
        this.V = in.readInt();
        if (V < 0) {
            throw new IllegalArgumentException("顶点(Vertex)数目不能小于0");
        }
        // 泛型数组的初始化,只能先声明泛型然后进行强制转换.Java本身决定地
        adj = (P98LinkedListBag<Integer>[]) new P98LinkedListBag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new P98LinkedListBag<>();
        }
        // 注意这里不能是this.E=in.readInt();因为下面在addEdge的时候回执行E++操作地
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边的数目不能小于0!");
        }
        // 依次读入一对对的边的顶点
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            addEdge(v, w);
        }
    }

    /**
     * 复制一个图
     *
     * @param graph 待被辅助的图
     */
    public P336Graph(P336Graph graph) {
        this(graph.V);
        this.E = graph.E;
        for (int v = 0; v < graph.V; v++) {
            // 用于先逆序一下,然后下面加到graph中才会和原始顺序一致
            P94LinkedListStack<Integer> reverseStack = new P94LinkedListStack<>();
            for (int w : graph.adj[v]) {
                reverseStack.push(w);
            }
            for (int w : reverseStack) {
                adj[v].add(w);
            }
        }
    }

    /**
     * 返回顶点数目
     */
    public int getV() {
        return V;
    }

    /**
     * 返回边的数目
     */
    public int getE() {
        return E;
    }

    /**
     * 判断定点编号是否在合理范围内[0~V)
     *
     * @param v 顶点编号
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

    /**
     * 在v和w之间连一条无向边
     *
     * @param v 编号为v的顶点
     * @param w 编号为w的顶点
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        // 相互之间都要把对方加入到邻接点数组(一个Bag)中
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * 返回一个可迭代的对象,此处为P98LinkedListBag<Bag>
     *
     * @param v 顶点
     * @return 可迭代的内部元素为整型的对象, 此处为Bag
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 返回一个顶点的邻接点的数目(也称为度)
     *
     * @param v 下标为v的顶点
     * @return 顶点v的邻接点数目(此处即为Bag的size)
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 返回图的字符串表示(顶点:所有邻接点)
     *
     * @return "顶点:所有邻接点"的字符串形式
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + "个顶点, " + E + "条边" + NEW_LINE);
        for (int v = 0; v < V; v++) {
            sb.append(v + ":");
            for (Integer w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
