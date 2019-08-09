package Chapter05BreadthFirstTraversal.Section7BiPartitionDetect;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    private static void showResult(String filePath) {
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphBFSBiPartitionDetect graphBFSBiPartitionDetect = new GraphBFSBiPartitionDetect(graph);
        System.out.println(graphBFSBiPartitionDetect.isBiPartition());
        System.out.println("当前图" + (graphBFSBiPartitionDetect.isBiPartition() ? "是" : "不是") + "二分图");
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section7BiPartitionDetect/graph.txt";
        showResult(filePath);
        filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section7BiPartitionDetect/graph2.txt";
        showResult(filePath);
        filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section7BiPartitionDetect/graph3.txt";
        showResult(filePath);
    }
}

/**
 * 输出是：
 * <p>
 * 顶点数V = 7, 边数E = 6
 * true
 * 当前图是二分图
 * 顶点数V = 4, 边数E = 6
 * false
 * 当前图不是二分图
 * 顶点数V = 4, 边数E = 4
 * true
 * 当前图是二分图
 */
