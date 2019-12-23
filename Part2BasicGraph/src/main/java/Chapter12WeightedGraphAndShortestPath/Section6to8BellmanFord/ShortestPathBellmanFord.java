/***********************************************************
 * @Description : 无向有权图的最短路径算法之Bellman算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/23 20:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section6to8BellmanFord;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.Arrays;

public class ShortestPathBellmanFord {
    /**
     * 无向有权图
     */
    private WeightedGraph graph;

    /**
     * 开始遍历的起始点
     */
    private int start;

    /**
     * 起始点start到每个顶点的最短距离
     */
    private int[] distances;


    /**
     * 是否存在负权环
     */
    private boolean negativeCycle = false;

    /**
     * 返回是否是负权环
     */
    public boolean isNegativeCycle() {
        return negativeCycle;
    }

    public ShortestPathBellmanFord(WeightedGraph graph, int start) {
        graph.validateVertex(start);
        this.start = start;
        this.graph = graph;
        this.distances = new int[graph.V()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        // 执行V-1次松弛操作
        for (int pass = 1; pass < graph.V(); pass++) {
            // 松弛所有的边
            for (int v = 0; v < graph.V(); v++) {
                for (int w : graph.adj(v)) {
                    // v到起点的距离不再是正无穷了，之前已经被更新为一个具体的值了；
                    if (distances[v] != Integer.MAX_VALUE) {
                        if (distances[v] + graph.getWeight(v, w) < distances[w]) {
                            // 对start-w进行start-v-w的松弛操作
                            distances[w] = distances[v] + graph.getWeight(v, w);
                        }
                    }
                }
            }
        }
        // 为了防止存在负权环，再进行一轮所有边的松弛操作
        // 松弛所有的边
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                // v到起点的距离不再是正无穷了，之前已经被更新为一个具体的值了；
                if (distances[v] != Integer.MAX_VALUE) {
                    if (distances[v] + graph.getWeight(v, w) < distances[w]) {
                        // 对start-w进行start-v-w的松弛操作，即更新最小距离值
                        // distances[w] = distances[v] + graph.getWeight(v, w);
                        // 第v轮还能进入更新最小值的条件语句，说明存在负权环
                        negativeCycle = true;
                    }
                }
            }
        }
    }

    /**
     * 判断顶点v和起始点start是否是联通，不是Integer.MAX_VALUE表示更新过了，即v和start是联通的
     */
    public boolean isConnectedTo(int v) {
        graph.validateVertex(v);
        return distances[v] != Integer.MAX_VALUE;
    }

    public int shortestDistanceTo(int v) {
        graph.validateVertex(v);
        if (negativeCycle){
            throw new RuntimeException("图中存在负权环！");
        }
        return distances[v];
    }
}
