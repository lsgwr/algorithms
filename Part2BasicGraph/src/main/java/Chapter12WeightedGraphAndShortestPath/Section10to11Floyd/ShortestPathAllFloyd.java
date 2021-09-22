/***********************************************************
 * @Description : Floyd算法求所有点对的最短路径
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/24 0:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section10to11Floyd;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.WeightedEdge;

import java.util.Arrays;

public class ShortestPathAllFloyd {
    /**
     * 无向有权图
     */
    private WeightedGraph graph;

    /**
     * 存储所有点对的最短路径，distances[v][w]表示v到w的最短路径
     */
    private int[][] distances;

    /**
     * 是否有负权环
     */
    private boolean negativeCycle;

    public ShortestPathAllFloyd(WeightedGraph graph) {
        this.graph = graph;

        floyd();
    }

    /**
     * Floyd算法求所有点对的最短路径
     */
    public void floyd() {
        int V = graph.V();
        distances = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        for (int v = 0; v < V; v++) {
            // 自己到自己的权值就是0
            distances[v][v] = 0;
            for (int w : graph.adj(v)) {
                // v到邻接边的距离初始化为边v-w的权重
                distances[v][w] = graph.getWeight(v, w);
            }
        }
        for (int t = 0; t < V; t++) {
            for (int v = 0; v < V; v++) {
                for (int w = 0; w < V; w++) {
                    // 边v-t和边t-w必须已经被访问了，初始值不再是Integer.MAX_VALUE
                    if (distances[v][t] != Integer.MAX_VALUE && distances[t][w] != Integer.MAX_VALUE) {
                        // 如果松弛一条边，v-t-w的距离小于v-w，则进行最小距离的更新
                        if (distances[v][t] + distances[t][w] < distances[v][w]) {
                            // 更新最小距离
                            distances[v][w] = distances[v][t] + distances[t][w];
                        }
                    }
                }
            }
        }
        for (int v = 0; v < V; v++) {
            if (distances[v][v] < 0) {
                negativeCycle = true;
            }
        }
    }

    /**
     * 图中是否有负权环
     *
     * @return
     */
    public boolean hasNegativeCycle() {
        return negativeCycle;
    }

    /**
     * 判断顶点v和顶点w是否是联通，不是Integer.MAX_VALUE表示更新过了，即v和w是联通的
     */
    public boolean isConnectedTo(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        if (negativeCycle) {
            throw new RuntimeException("图中存在负权环！");
        }
        return distances[v][w] != Integer.MAX_VALUE;
    }

    /**
     * 顶点v和w之间的最小距离
     */
    public int shortestDistanceBetween(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        if (negativeCycle) {
            throw new RuntimeException("图中存在负权环！");
        }
        return distances[v][w];
    }
}
