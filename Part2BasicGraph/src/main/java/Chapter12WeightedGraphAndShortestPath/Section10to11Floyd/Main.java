/***********************************************************
 * @Description : 测试Floyd算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/24 7:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section10to11Floyd;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        // 1.不存在负权边
        String filepath = "src/main/java/Chapter12WeightedGraphAndShortestPath/Section1to3Dijkstra/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        ShortestPathAllFloyd floyd = new ShortestPathAllFloyd(graph);
        if (floyd.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                for (int w = 0; w < graph.V(); w++) {
                    System.out.println(v + "到" + w + "的最短距离为：" + floyd.shortestDistanceBetween(v, w));
                }
                System.out.println();
            }
        }

        // 2.存在负权边
        filepath = "src/main/java/Chapter12WeightedGraphAndShortestPath/Section6to8BellmanFord/graph.txt";
        graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        floyd = new ShortestPathAllFloyd(graph);
        if (floyd.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                for (int w = 0; w < graph.V(); w++) {
                    System.out.println(v + "到" + w + "的最短距离为：" + floyd.shortestDistanceBetween(v, w));
                }
                System.out.println();
            }
        }
    }
}
/**
 * 顶点数V = 5, 边数E = 8
 * 0到0的最短距离为：0
 * 0到1的最短距离为：3
 * 0到2的最短距离为：2
 * 0到3的最短距离为：5
 * 0到4的最短距离为：6
 *
 * 1到0的最短距离为：3
 * 1到1的最短距离为：0
 * 1到2的最短距离为：1
 * 1到3的最短距离为：2
 * 1到4的最短距离为：3
 *
 * 2到0的最短距离为：2
 * 2到1的最短距离为：1
 * 2到2的最短距离为：0
 * 2到3的最短距离为：3
 * 2到4的最短距离为：4
 *
 * 3到0的最短距离为：5
 * 3到1的最短距离为：2
 * 3到2的最短距离为：3
 * 3到3的最短距离为：0
 * 3到4的最短距离为：1
 *
 * 4到0的最短距离为：6
 * 4到1的最短距离为：3
 * 4到2的最短距离为：4
 * 4到3的最短距离为：1
 * 4到4的最短距离为：0
 *
 * 顶点数V = 5, 边数E = 8
 * 图中存在负权环！
 */