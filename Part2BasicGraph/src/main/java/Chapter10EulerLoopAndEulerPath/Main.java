/***********************************************************
 * @Description : 欧拉回路测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 10:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10EulerLoopAndEulerPath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1.基于非递归的DFS实现求欧拉回路
        String filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        List<Integer> loop1 = new GraphDFSNoRecursionEulerLoop(graph, 0).getLoop();
        System.out.println(loop1);

        filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        List<Integer> loop2 = new GraphDFSNoRecursionEulerLoop(graph, 0).getLoop();
        System.out.println(loop2);
        System.out.println();

        // 2.基于递归的DFS实现求欧拉回路
        filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        List<Integer> loop3 = new GraphDFSEulerLoop(graph, 0).getLoop();
        System.out.println(loop3);

        filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        List<Integer> loop4 = new GraphDFSEulerLoop(graph, 0).getLoop();
        System.out.println(loop4);
    }
}
/**
 * 顶点数V = 5, 边数E = 6
 * [0, 2, 4, 3, 2, 1, 0]
 * 顶点数V = 11, 边数E = 15
 * [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]
 *
 * 顶点数V = 5, 边数E = 6
 * [0, 2, 4, 3, 2, 1, 0]
 * 顶点数V = 11, 边数E = 15
 * [0, 3, 4, 6, 7, 9, 10, 8, 7, 5, 4, 1, 5, 2, 1, 0]
 */
