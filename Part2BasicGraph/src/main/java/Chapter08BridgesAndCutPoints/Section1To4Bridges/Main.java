/***********************************************************
 * @Description : 桥的检测 测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/19 19:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08BridgesAndCutPoints.Section1To4Bridges;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter08BridgesAndCutPoints/Section1To4Bridges/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("图中包含的桥为：");
        System.out.println(new GraphDFSFindBridges(graph).getBridges());
        System.out.println();

        filePath = "src/main/java/Chapter08BridgesAndCutPoints/Section1To4Bridges/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("图中包含的桥为：");
        System.out.println(new GraphDFSFindBridges(graph).getBridges());
        System.out.println();

        filePath = "src/main/java/Chapter08BridgesAndCutPoints/Section1To4Bridges/tree.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("图中包含的桥为：");
        System.out.println(new GraphDFSFindBridges(graph).getBridges());
    }
}
/**
 * 顶点数V = 7, 边数E = 8
 * 图中包含的桥为：[3-5]
 *
 * 顶点数V = 12, 边数E = 16
 * 图中包含的桥为：[6-8, 4-7, 3-5]
 *
 * 顶点数V = 7, 边数E = 6
 * 图中包含的桥为：[1-6, 0-1, 2-5, 3-2, 3-4, 0-3]
 */