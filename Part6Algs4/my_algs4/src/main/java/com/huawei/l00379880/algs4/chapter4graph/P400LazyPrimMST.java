/***********************************************************
 * @Description : 延时删除无效边的最小生成树算法
 *                MST: minimum spanning tree
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/22 上午12:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import com.huawei.l00379880.algs4.chapter2sort.P202MinPQ;
import com.huawei.l00379880.mylib.math.UF;

public class P400LazyPrimMST {
    /**
     * 随意的浮点数
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * 最小生成树所有边的权重之和
     */
    private double weight;
    /**
     * 存储组成最小生成树的边
     */
    private P95LinkedListQueue<P394Edge> mst;
    /**
     * marked[v] = true 代表点v在最小生成树上
     */
    private boolean[] marked;
    /**
     * 用于存储横切边(包括失效边)的最小优先队列,Priority Queue
     */
    private P202MinPQ<P394Edge> pq;

    public P400LazyPrimMST(P395EdgeWeightedGraph graph) {
        mst = new P95LinkedListQueue<>();
        pq = new P202MinPQ<>();
        marked = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!marked[v]) {
                prim(graph, v);
            }
        }

        // 检查图是否符合规范
        assert check(graph);
    }

    /**
     * prim算法，最后删除失效边
     *
     * @param graph 待扫描的图
     * @param s     扫描的起点
     */
    private void prim(P395EdgeWeightedGraph graph, int s) {
        scan(graph, s);
        while (!pq.isEmpty()) {
            P394Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            assert marked[v] || marked[w];
            // 一条边上的两个点都标记过，就跳过
            if (marked[v] && marked[w]) {
                continue;
            }
            // 添加到最小生成树
            mst.enqueue(edge);
            weight += edge.getWeight();
            // v becomes part of tree
            if (!marked[v]) {
                scan(graph, v);
            }
            // w becomes part of tree
            if (!marked[w]) {
                scan(graph, w);
            }
        }
    }

    private void scan(P395EdgeWeightedGraph graph, int v) {
        assert !marked[v];
        marked[v] = true;
        for (P394Edge edge : graph.getAdj(v)) {
            if (!marked[edge.other(v)]) {
                pq.insert(edge);
            }
        }
    }

    public Iterable<P394Edge> edges() {
        return mst;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * 检查当前的图是否符合要求
     */
    private boolean check(P395EdgeWeightedGraph graph) {
        // 1.check weight
        double totalWeight = 0.0;
        for (P394Edge edge : edges()) {
            totalWeight += edge.getWeight();
        }
        // 2.浮点数的精准计算
        if (Math.abs(totalWeight - getWeight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("最小生成树的边的重量不等于weight()函数获取的值：%f vs. %d\n");
            return false;
        }
        // 3.确保图是无环图
        UF uf = new UF(graph.getV());
        for (P394Edge edge : edges()) {
            int v = edge.either();
            int w = edge.other(v);
            if (uf.connected(v, w)) {
                System.err.println("不是无环图！");
                return false;
            }
            uf.union(v, w);
        }
        // 4.确保是一个生成树
        for (P394Edge edge : graph.edges()) {
            int v = edge.either();
            int w = edge.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("不是一个合格的生成树森林！");
                return false;
            }
        }
        // 5.检查是不是最小生成树
        for (P394Edge e : edges()) {
            uf = new UF(graph.getV());
            for (P394Edge f : mst) {
                int x = f.either();
                int y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }
            // 找到权重最小的边
            for (P394Edge edge : graph.edges()) {
                int x = edge.either();
                int y = edge.other(x);
                if (!uf.connected(x, y)) {
                    if (edge.getWeight() < e.getWeight()) {
                        System.err.println("Edge " + edge + " violates cut optimality conditions");
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
