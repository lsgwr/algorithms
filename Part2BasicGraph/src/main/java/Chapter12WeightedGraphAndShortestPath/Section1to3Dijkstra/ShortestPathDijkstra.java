/***********************************************************
 * @Description : 无向有权图的最短路径算法之Dijkstra算法，时间复杂度为O(V^2)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 21:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section1to3Dijkstra;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.Arrays;

public class ShortestPathDijkstra {
    /**
     * 无向有权图
     */
    private WeightedGraph graph;
    /**
     * 求最短路径的起点，用户指定
     */
    private int start;
    /**
     * 各个定点到起始点的距离
     */
    private int[] distances;

    /**
     * 是否找到了顶点到起始点的最小距离值
     */
    private boolean[] findShortest;

    public ShortestPathDijkstra(WeightedGraph graph, int start) {
        graph.validateVertex(start);
        this.graph = graph;
        this.start = start;

        distances = new int[graph.V()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // 起始点到起始点的最短距离为0
        distances[start] = 0;
        // 初始化所有的节点都没找到最短路径
        findShortest = new boolean[graph.V()];
        Arrays.fill(findShortest, false);
        dijkstra();
    }

    public void dijkstra() {
        while (true) {
            /**
             * 步骤1：确认一个顶点的距离值为到顶点的最小距离值
             */
            // 本次循环的最小值的顶点编号
            int curV = -1;
            // 本次循环的最小值顶点对应的最小值
            int curDis = Integer.MAX_VALUE;
            for (int v = 0; v < graph.V(); v++) {
                // 遍历所有没有确定到起始点最小距离值的顶点
                if (!findShortest[v]) {
                    // 不断更新这些点中到起始点距离最小的点和其距离值
                    if (distances[v] < curDis) {
                        curV = v;
                        curDis = distances[v];
                    }
                }
            }
            // 所有的点的顶点都已经找到到起始点的最小距离值了，就退出for循环
            if (curV == -1) {
                break;
            }
            // 0到curV的最短路径就确定了
            findShortest[curV] = true;

            /**
             * 步骤2：根据上面确认的最小距离值的顶点，更新起始点到其邻接点的距离值
             */
            for (int w : graph.adj(curV)) {
                if (!findShortest[w]) {
                    if (distances[curV] + graph.getWeight(curV, w) < distances[w]) {
                        distances[w] = distances[curV] + graph.getWeight(curV, w);
                    }
                }
            }
        }
    }

    /**
     * 获取起始点start到顶点v的最小距离值
     */
    public int[] getDistances() {
        return distances;
    }

    /**
     * 判断顶点v到定点start之间是否连通
     */
    public boolean isConnectedTo(int v) {
        graph.validateVertex(v);
        return findShortest[v];
    }

    /**
     * 起点start到定点v的最小距离值
     */
    public int shortestDistanceTo(int v){
       graph.validateVertex(v);
       return distances[v];
    }
}
