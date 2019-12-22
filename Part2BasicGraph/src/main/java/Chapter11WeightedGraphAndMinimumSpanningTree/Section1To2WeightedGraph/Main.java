/***********************************************************
 * @Description : 测试我们的无向带权图
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 17:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter11WeightedGraphAndMinimumSpanningTree/Section1WeightedGraph/graph.txt";
        WeightedGraph graph = new WeightedGraph(false);
        ReadWeightedGraph.init(graph, filepath);
        System.out.println(graph);
    }
}
/**
 * 顶点数V = 7, 边数E = 12
 * 顶点数V = 7, 边数E = 12，完整的邻接表信息如下：
 * v=0: (w=1, weight=2) (w=3, weight=7) (w=5, weight=2)
 * v=1: (w=0, weight=2) (w=2, weight=1) (w=3, weight=4) (w=4, weight=3) (w=5, weight=5)
 * v=2: (w=1, weight=1) (w=4, weight=4) (w=5, weight=4)
 * v=3: (w=0, weight=7) (w=1, weight=4) (w=4, weight=1) (w=6, weight=5)
 * v=4: (w=1, weight=3) (w=2, weight=4) (w=3, weight=1) (w=6, weight=7)
 * v=5: (w=0, weight=2) (w=1, weight=5) (w=2, weight=4)
 * v=6: (w=3, weight=5) (w=4, weight=7)
 */
