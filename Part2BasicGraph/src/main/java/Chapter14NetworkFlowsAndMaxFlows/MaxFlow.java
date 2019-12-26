/***********************************************************
 * @Description : 网络最大流问题(基于多次BFS进行查找)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/26 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14NetworkFlowsAndMaxFlows;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

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
    }
}
