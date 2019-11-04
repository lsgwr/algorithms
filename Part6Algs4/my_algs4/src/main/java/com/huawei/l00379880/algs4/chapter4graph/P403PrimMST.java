/***********************************************************
 * @Description : 最小生成树的标准算法。实时删除失效边
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/22 下午10:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import com.huawei.l00379880.algs4.chapter2sort.P202MinPQ;
import com.huawei.l00379880.algs4.chapter2sort.P203IndexMinPQ;
import com.huawei.l00379880.mylib.math.UF;

public class P403PrimMST {
    /**
     * 随意的浮点数
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * edgeTo[v]:从非最小生成树节点到最小生成树节点的最短边(edge)
     * edgeTo[v] = shortest edge from tree vertex to non-tree vertex
     */
    private P394Edge[] edgeTo;
    /**
     * distTo[v]:上面所说的边的权重
     */
    private double[] distTo;
    /**
     * marked[v]:v点是否已经被标记过了。
     */
    private boolean[] marked;

    /**
     * 索引优先队列
     */
    private P203IndexMinPQ<Double> pq;

    public P403PrimMST(P395EdgeWeightedGraph graph) {
        int V = graph.getV();
        edgeTo = new P394Edge[V];
        distTo = new double[V];
        marked = new boolean[V];
        pq = new P203IndexMinPQ<>(V);
        for (int v = 0; v < V; v++) {
            // 初始化都是正无穷，这样不会影响最小值的获取
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                prim(graph, v);
            }
        }
        assert check(graph);
    }


    /**
     * prim算法，实时删除失效的边，索引优先队列中只保留最小的边
     *
     * @param graph 指定的带权重的图
     * @param s     开始遍历的起点
     */
    private void prim(P395EdgeWeightedGraph graph, int s) {
        // 初始化第一个点
        distTo[s] = 0.0;
        // 记录到下标为s点的最小巨鹿
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(graph, v);
        }
    }

    /**
     * 扫描顶点v
     */
    private void scan(P395EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (P394Edge edge : graph.getAdj(v)) {
            int w = edge.other(v);
            if (marked[w]) {
                continue;
            }
            if (edge.getWeight() < distTo[w]) {
                // 更新最小距离
                distTo[w] = edge.getWeight();
                // 更新最小边
                edgeTo[w] = edge;
                if (pq.contains(w)) {
                    //如果之前存在w对应的索引就进行更新
                    pq.decreaseKey(w, distTo[w]);
                } else {
                    // 之前不含有的话就新插入
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    /**
     * 获取最小生成树中的所有边组成的Queue
     */
    public Iterable<P394Edge> edges() {
        P95LinkedListQueue<P394Edge> mst = new P95LinkedListQueue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            P394Edge edge = edgeTo[v];
            if (edge != null) {
                mst.enqueue(edge);
            }
        }
        return mst;
    }

    /**
     * 获取最小生成树的总权重
     */
    public double getWeight() {
        double weight = 0.0;
        for (P394Edge edge : edges()) {
            weight += edge.getWeight();
        }
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
            for (P394Edge f : edges()) {
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
                    }
                }
            }
        }

        return true;
    }
}
