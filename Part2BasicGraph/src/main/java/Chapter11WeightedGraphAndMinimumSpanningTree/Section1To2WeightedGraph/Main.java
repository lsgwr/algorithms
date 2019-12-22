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
