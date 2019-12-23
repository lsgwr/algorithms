/***********************************************************
 * @Description : 最短路径算法Dijkstra算法测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/23 17:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section4DijkstraOptimize;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter12WeightedGraphAndShortestPath/Section1to3Dijkstra/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        int start = 0;
        ShortestPathDijkstra dijkstra = new ShortestPathDijkstra(graph, start);
        for (int v = 0; v < graph.V(); v++) {
            System.out.println(start + "到" + v + "的最短距离为：" + dijkstra.shortestDistanceTo(v) + ", 路径详情是：" + dijkstra.getPath(v));
        }
    }
}
/**
 * 顶点数V = 5, 边数E = 8
 * 0到0的最短距离为：0, 路径详情是：[0]
 * 0到1的最短距离为：3, 路径详情是：[0, 2, 1]
 * 0到2的最短距离为：2, 路径详情是：[0, 2]
 * 0到3的最短距离为：5, 路径详情是：[0, 2, 1, 3]
 * 0到4的最短距离为：6, 路径详情是：[0, 2, 1, 4]
 */