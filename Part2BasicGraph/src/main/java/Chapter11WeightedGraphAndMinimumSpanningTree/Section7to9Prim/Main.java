/***********************************************************
 * @Description : Prim算法测试
 * 边的输出顺序和Kruskal不同，但是具体的边都是一样地
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 21:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section7to9Prim;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter11WeightedGraphAndMinimumSpanningTree/Section3to5Kruskal/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        MinimumSpanningTreePrim prim = new MinimumSpanningTreePrim(graph);
        System.out.println(prim.getMst());

        MinimumSpanningTreePrimBasic primBasic = new MinimumSpanningTreePrimBasic(graph);
        System.out.println(primBasic.getMst());
    }
}
/**
 * 顶点数V = 7, 边数E = 12
 * [(0-1: 2), (1-2: 1), (0-5: 2), (1-4: 3), (4-3: 1), (3-6: 5)]
 */