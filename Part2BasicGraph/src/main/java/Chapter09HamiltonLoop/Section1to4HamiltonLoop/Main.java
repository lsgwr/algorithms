/***********************************************************
 * @Description : 哈密尔顿回路测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-20 14:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section1to4HamiltonLoop;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter09HamiltonLoop/Section1to4HamiltonLoop/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("哈密尔顿回路详情为：");
        List<Integer> path = new GraphDFSHamiltonLoop(graph).getLoop();
        System.out.println(path);
        System.out.println();

        filePath = "src/main/java/Chapter09HamiltonLoop/Section1to4HamiltonLoop/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("哈密尔顿回路详情为：");
        path = new GraphDFSHamiltonLoop(graph).getLoop();
        System.out.println(path);
    }
}
/**
 * 顶点数V = 4, 边数E = 5
 * 哈密尔顿回路详情为：[0, 2, 1, 3, 0]
 *
 * 顶点数V = 20, 边数E = 30
 * 哈密尔顿回路详情为：[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 19, 18, 17, 16, 15, 14, 13, 0]
 */