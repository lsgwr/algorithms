package Chapter04DFSInAction.Section10BiPartitionDetect;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

/***********************************************************
 * @note      : 
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/8 11:23
 ***********************************************************/
public class Main {

    private static void showResult(String filePath) {
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFSBiPartitionDetect graphDFSBiPartitionDetect = new GraphDFSBiPartitionDetect(graph);
        System.out.println(graphDFSBiPartitionDetect.isBiPartition());
        System.out.println("当前图" + (graphDFSBiPartitionDetect.isBiPartition() ? "是" : "不是") + "二分图");
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/graph.txt";
        showResult(filePath);
        filePath = "src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/graph2.txt";
        showResult(filePath);
        filePath = "src/main/java/Chapter04DFSInAction/Section10BiPartitionDetect/graph3.txt";
        showResult(filePath);
    }
}

/**
 * 结果输出：
 * 
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
