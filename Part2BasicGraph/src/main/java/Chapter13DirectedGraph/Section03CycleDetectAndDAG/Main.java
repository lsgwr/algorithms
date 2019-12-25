/***********************************************************
 * @Description : 有向图环检测
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/25 7:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section03CycleDetectAndDAG;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    private static void showResult(String filePath) {
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filePath);
        GraphDFSCycleDetectDirected cycleDetect = new GraphDFSCycleDetectDirected(graph);
        System.out.println("当前图中" + (cycleDetect.hasCycle() ? "有" : "无") + "环");
    }

    public static void main(String[] args) {
        // 1.无环
        String filePath = "src/main/java/Chapter13DirectedGraph/Section03CycleDetectAndDAG/有向无权无权图.txt";
        showResult(filePath);

        // 2.有环
        filePath = "src/main/java/Chapter13DirectedGraph/Section03CycleDetectAndDAG/有向无权有环图.txt";
        showResult(filePath);
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 当前图中无环
 * 顶点数V = 5, 边数E = 6
 * 当前图中有环
 */