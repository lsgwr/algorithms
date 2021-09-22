/***********************************************************
 * @Description : 求有向有权图的最短路径，直接使用上一章的的Bellman-Ford算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/24 19:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section02CommonAlgorithms;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter12WeightedGraphAndShortestPath.Section6to8BellmanFord.ShortestPathBellmanFord;

public class ShortestPathBellmanFordTest {
    public static void main(String[] args) {
        // 1.无负权环
        String filepath = "src/main/java/Chapter13DirectedGraph/Section02CommonAlgorithms/有负权边无负权环图.txt";
        WeightedGraph graph = new WeightedGraph(true);
        ReadWeightedGraph.init(graph, filepath);
        int start = 0;
        ShortestPathBellmanFord bellmanFord = new ShortestPathBellmanFord(graph, start);
        if (bellmanFord.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                System.out.println(start + "到" + v + "的最短距离为：" + bellmanFord.shortestDistanceTo(v));
                System.out.println(start + "到" + v + "的最短路径为" + bellmanFord.getPath(v));
            }
        }
        System.out.println();

        // 2.无负权环
        filepath = "src/main/java/Chapter13DirectedGraph/Section02CommonAlgorithms/有负权边有负权环图.txt";
        graph = new WeightedGraph(true);
        ReadWeightedGraph.init(graph, filepath);
        start = 0;
        bellmanFord = new ShortestPathBellmanFord(graph, start);
        if (bellmanFord.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                System.out.println(start + "到" + v + "的最短距离为：" + bellmanFord.shortestDistanceTo(v));
                System.out.println(start + "到" + v + "的最短路径为" + bellmanFord.getPath(v));
            }
        }

    }
}
/**
 * 顶点数V = 3, 边数E = 3
 * 0到0的最短距离为：0
 * 0到0的最短路径为[0]
 * 0到1的最短距离为：-3
 * 0到1的最短路径为[0, 2, 1]
 * 0到2的最短距离为：3
 * 0到2的最短路径为[0, 2]
 *
 * 顶点数V = 3, 边数E = 3
 * 图中存在负权环！
 */