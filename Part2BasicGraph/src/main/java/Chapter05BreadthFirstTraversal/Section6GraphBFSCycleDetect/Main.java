package Chapter05BreadthFirstTraversal.Section6GraphBFSCycleDetect;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    private static void showResult(String filePath) {
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphBFSCycleDetect graphBFSCycleDetect = new GraphBFSCycleDetect(graph);
        System.out.println("当前图中" + (graphBFSCycleDetect.hasCycle() ? "有" : "无") + "环");
    }

    public static void main(String[] args) {
        // graph有环
        String filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section6GraphBFSCycleDetect/graph.txt";
        showResult(filePath);
        // graph2.txt无环
        filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section6GraphBFSCycleDetect/graph2.txt";
        showResult(filePath);
    }
}

/**
 * 输出结果
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 当前图中有环
 * 顶点数V = 7, 边数E = 5
 * 当前图中无环
 */
