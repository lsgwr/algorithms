/***********************************************************
 * @Description : 基于Kruskal算法生成最小生成树
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/22 下午11:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import com.huawei.l00379880.algs4.chapter2sort.P202MinPQ;
import com.huawei.l00379880.mylib.math.UF;

public class P406KruskalMST {
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
    private P95LinkedListQueue<P394Edge> mst = new P95LinkedListQueue<>();

    public P406KruskalMST(P395EdgeWeightedGraph graph) {
        P202MinPQ<P394Edge> pq = new P202MinPQ<>();
        for (P394Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        // 运行贪心算法
        UF uf = new UF(graph.getV());
        // 队列不为空并且还没获取最小生成树需要的最小边数
        while (!pq.isEmpty() && mst.size() < graph.getV() - 1) {
            P394Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (!uf.connected(v, w)) {
                // 如果不会形成环的话(忽略已经加入到优先队列中的顶点能组成的边)
                uf.union(v, w);
                mst.enqueue(edge);
                weight += edge.getWeight();
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
