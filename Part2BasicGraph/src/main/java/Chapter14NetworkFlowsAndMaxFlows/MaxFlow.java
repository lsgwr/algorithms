/***********************************************************
 * @Description : 网络最大流问题(基于多次BFS进行查找)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/26 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14NetworkFlowsAndMaxFlows;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.List;

public class MaxFlow {
    /**
     * 有向有权图作为网络
     */
    private WeightedGraph networkGraph;

    /**
     * 残量图
     */
    private WeightedGraph residualGraph;
    /**
     * 源点s
     */
    private int source;
    /**
     * 汇点t
     */
    private int target;
    /**
     * 最大流的值
     */
    private int maxFlow;

    public MaxFlow(WeightedGraph networkGraph, int source, int target) {
        if (!networkGraph.isDirected()) {
            throw new IllegalArgumentException("最大流算法只支持有向图！");
        }
        if (networkGraph.V() < 2) {
            throw new IllegalArgumentException("网络流问题的图应该至少2条边!");
        }
        networkGraph.validateVertex(source);
        networkGraph.validateVertex(target);
        if (source == target) {
            throw new IllegalArgumentException("源点和汇点必须是不同的图");
        }
        this.networkGraph = networkGraph;
        this.source = source;
        this.target = target;

        this.residualGraph = new WeightedGraph(networkGraph.V(), true);

    }

    /**
     * 求最大流的Edmonds-Karp算法
     */
    public void edmondsKarp() {
        for (int v = 0; v < networkGraph.V(); v++) {
            for (int w : networkGraph.adj(v)) {
                // 边的权值c
                int capacity = networkGraph.getWeight(v, w);
                // 初始化残量图
                residualGraph.addEdge(v, w, capacity);
                residualGraph.addEdge(w, v, 0);
            }
        }
        // 不断进行bfs寻找增广路径
        while (true) {
            List<Integer> augPath = getAugmentingPath();
            if (augPath.size() == 0) {
                break;
            }
            // 增广路径上的流量f
            int flow = Integer.MAX_VALUE;
            // Todo:计算增广路径的权值最小值，作为增广路径的流量
            // 不断累积每条增广路径的流量，最终找完所有的增广路径就得到了最终的网络最大流
            maxFlow += flow;
        }
    }

    /**
     * 获取增广路径上所有顶点的集合
     */
    private List<Integer> getAugmentingPath() {
        // Todo:获取增广路径
        return null;
    }
}
