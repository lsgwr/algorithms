/***********************************************************
 * @Description : 二分图背景非匹配问题(最大匹配和完全匹配)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 14:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section1to3Matching;

import Chapter02GraphExpress.Graph;
import Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter14NetworkFlowsAndMaxFlows.MaxFlow;

public class BipartiteMaxMatching {
    /**
     * 无向图，必须是二分图
     */
    private Graph graph;

    /**
     * 最大匹配的顶点数
     */
    private int maxMatch;

    /**
     * 用于网络最大流问题的有向有权图
     */
    private WeightedGraph networkWeightedGraph;

    /**
     * 二分图中分成两部分的点，一半是0，一半是1
     */
    private int[] colors;

    public BipartiteMaxMatching(Graph graph) {
        // 上来先检测下是否是二分图
        GraphDFSBiPartitionDetect biPartitionDetect = new GraphDFSBiPartitionDetect(graph);
        if (!biPartitionDetect.isBiPartition()) {
            throw new IllegalArgumentException("传入的图不是二分图！");
        }
        // 把二分图具体的点划分拿到
        this.colors = biPartitionDetect.getColors();
        this.graph = graph;
        maxMatching();
    }

    /**
     * 最大匹配问题
     */
    public void maxMatching() {
        int V = graph.V();
        int s = V;
        int t = V + 1;
        // 基于二分图创建网络流模型。需要自己加上源点s和汇点t，在下面图中的顶点编号分别为V和V+1
        WeightedGraph networkGraph = new WeightedGraph(V + 2, true);
        // 加入地所有边的权值都为1
        for (int v = 0; v < V; v++) {
            if (colors[v] == 0) {
                // 从源点指向顶点所有颜色为0的二分图中的点
                networkGraph.addEdge(s, v, 1);
            } else {
                // graph中的颜色为1的点
                networkGraph.addEdge(v, t, 1);
            }
            for (int w : graph.adj(v)) {
                // 因为是有向图，为了防止v->w和w->v被加入两次，所以需要v<w
                if (v < w) {
                    if (colors[v] == 0) {
                        // 二分图中从颜色为0的点指向颜色为1的点
                        networkGraph.addEdge(v, w, 1);
                    } else {
                        // 二分图中从颜色为0的点指向颜色为1的点
                        networkGraph.addEdge(w, v, 1);
                    }
                }
            }
        }

        MaxFlow maxFlow = new MaxFlow(networkGraph, s, t);
        maxMatch = maxFlow.getMaxFlow();
    }

    /**
     * 最大匹配的个数
     */
    public int getMaxMatch() {
        return maxMatch;
    }
}
