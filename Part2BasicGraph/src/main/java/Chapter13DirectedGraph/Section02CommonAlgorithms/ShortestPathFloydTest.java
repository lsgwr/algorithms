/***********************************************************
 * @Description : 求有向有权图的最短路径，直接使用上一章的的Floyd算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/24 19:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section02CommonAlgorithms;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter12WeightedGraphAndShortestPath.Section10to11Floyd.ShortestPathAllFloyd;

public class ShortestPathFloydTest {
    public static void main(String[] args) {
        // 1.不存在负权边
        String filepath = "src/main/java/Chapter13DirectedGraph/Section02CommonAlgorithms/有负权边无负权环图.txt";
        WeightedGraph graph = new WeightedGraph(true);
        ReadWeightedGraph.init(graph, filepath);
        ShortestPathAllFloyd floyd = new ShortestPathAllFloyd(graph);
        if (floyd.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                for (int w = 0; w < graph.V(); w++) {
                    // 2147483647即Integer.MAX_VALUE，表示没有路径可走，自然没有最短距离
                    System.out.println(v + "到" + w + "的最短距离为：" + floyd.shortestDistanceBetween(v, w));
                }
                System.out.println();
            }
        }

        filepath = "src/main/java/Chapter13DirectedGraph/Section02CommonAlgorithms/有负权边有负权环图.txt";
        graph = new WeightedGraph(true);
        ReadWeightedGraph.init(graph, filepath);
        floyd = new ShortestPathAllFloyd(graph);
        if (floyd.hasNegativeCycle()) {
            System.out.println("图中存在负权环！");
        } else {
            for (int v = 0; v < graph.V(); v++) {
                for (int w = 0; w < graph.V(); w++) {
                    // 2147483647即Integer.MAX_VALUE，表示没有路径可走，自然没有最短距离
                    System.out.println(v + "到" + w + "的最短距离为：" + floyd.shortestDistanceBetween(v, w));
                }
                System.out.println();
            }
        }
    }
}
/**
 * 顶点数V = 3, 边数E = 3
 * 0到0的最短距离为：0
 * 0到1的最短距离为：-3
 * 0到2的最短距离为：3
 *
 * 1到0的最短距离为：2147483647
 * 1到1的最短距离为：0
 * 1到2的最短距离为：2147483647
 *
 * 2到0的最短距离为：2147483647
 * 2到1的最短距离为：-6
 * 2到2的最短距离为：0
 *
 * 顶点数V = 3, 边数E = 3
 * 图中存在负权环！
 */