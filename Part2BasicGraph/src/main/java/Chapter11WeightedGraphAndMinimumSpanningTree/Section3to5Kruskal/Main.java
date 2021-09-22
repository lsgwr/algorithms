/***********************************************************
 * @Description : 测试Kruskal算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 21:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter11WeightedGraphAndMinimumSpanningTree/Section3to5Kruskal/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        MinimumSpanningTreeKruskal kruskal = new MinimumSpanningTreeKruskal(graph);
        System.out.println(kruskal.getMst());
    }
}
/**
 * 顶点数V = 7, 边数E = 12
 * [(1-2: 1), (3-4: 1), (0-1: 2), (0-5: 2), (1-4: 3), (3-6: 5)]
 */
