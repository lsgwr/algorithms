/***********************************************************
 * @Description : 基于DepthFirstOrder和SymbolDigraph的有向无环图的拓扑排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/20 下午6:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P376Topological {
    /**
     * topological order
     */
    private Iterable<Integer> order;
    /**
     * rank[v] = position of vertex v in topological order
     */
    private int[] rank;

    /**
     * 有向图的拓扑排序
     *
     * @param digraph
     */
    public P376Topological(P366Digraph digraph) {
        P372DirectedCycle cycleFinder = new P372DirectedCycle(digraph);
        if (!cycleFinder.hasCycle()) {
            P374DepthFirstOrder dfs = new P374DepthFirstOrder(digraph);
            order = dfs.reversePostOrderQueue();
            rank = new int[digraph.getV()];
            int i = 0;
            for (int v : order) {
                rank[v] = i++;
            }
        }
    }

    /**
     * 加权有向图的拓扑排序
     * Determines whether the edge-weighted digraph {@code G} has a topological
     * order and, if so, finds such an order.
     */
    public P376Topological(P415EdgeWeightedDigraph digraph) {
        P416EdgeWeightedDirectedCycle finder = new P416EdgeWeightedDirectedCycle(digraph);
        if (!finder.hasCycle()) {
            P374DepthFirstOrder dfs = new P374DepthFirstOrder(digraph);
            order = dfs.reversePostOrderQueue();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    /**
     * 获取指定节点的拓扑排名
     *
     * @param v 指定下标的顶点
     * @return
     */
    public int getRank(int v) {
        validateVertex(v);
        if (hasOrder()) {
            return rank[v];
        } else {
            return -1;
        }
    }

    /**
     * 判断定点编号是否在合理范围内[0~V)
     *
     * @param v 顶点编号
     */
    private void validateVertex(int v) {
        // 顶点的总数目
        int V = rank.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

}
