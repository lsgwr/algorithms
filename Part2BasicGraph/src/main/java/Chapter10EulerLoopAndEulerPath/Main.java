/***********************************************************
 * @Description : 欧拉回路测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 10:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10EulerLoopAndEulerPath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFSNoRecursionEulerLoop(graph).dfs());

        filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFSNoRecursionEulerLoop(graph).dfs());
    }
}
/**
 * 顶点数V = 5, 边数E = 6
 * [0, 2, 4, 3, 2, 1, 0]
 * 顶点数V = 11, 边数E = 15
 * [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]
 */
