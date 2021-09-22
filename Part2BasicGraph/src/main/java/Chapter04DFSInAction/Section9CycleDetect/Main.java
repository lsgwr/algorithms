package Chapter04DFSInAction.Section9CycleDetect;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

/***********************************************************
 * @note      : 检测无向图中的环 测试
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/8 10:18
 ***********************************************************/
public class Main {

    private static void showResult(String filePath) {
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFSCycleDetect graphDFSCycleDetect = new GraphDFSCycleDetect(graph);
        System.out.println("当前图中" + (graphDFSCycleDetect.hasCycle() ? "有" : "无") + "环");
    }

    public static void main(String[] args) {
        // graph有环
        String filePath = "src/main/java/Chapter04DFSInAction/Section9CycleDetect/graph.txt";
        showResult(filePath);
        // graph2.txt无环
        filePath = "src/main/java/Chapter04DFSInAction/Section9CycleDetect/graph2.txt";
        showResult(filePath);
    }
}

/**
 * 输出结果：
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 当前图中有环
 * 顶点数V = 7, 边数E = 5
 * 当前图中无环
 * </p>
 **/
