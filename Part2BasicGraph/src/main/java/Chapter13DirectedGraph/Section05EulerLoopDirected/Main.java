/***********************************************************
 * @Description : 有向图欧拉回路测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/25 17:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section05EulerLoopDirected;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1.递归有欧拉回路
        String filepath = "src/main/java/Chapter13DirectedGraph/Section05EulerLoopDirected/有向无权有欧拉回路图.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        GraphDFSEulerLoopDirected eulerLoopDirected = new GraphDFSEulerLoopDirected(graph, 0);
        List<Integer> loop = eulerLoopDirected.getLoop();
        System.out.println(loop);
        System.out.println();

        // 2.递归无欧拉回路
        filepath = "src/main/java/Chapter13DirectedGraph/Section05EulerLoopDirected/有向无权无欧拉回路图.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        eulerLoopDirected = new GraphDFSEulerLoopDirected(graph, 0);
        loop = eulerLoopDirected.getLoop();
        System.out.println(loop);
        System.out.println();

        // 3.非递归有欧拉回路
        filepath = "src/main/java/Chapter13DirectedGraph/Section05EulerLoopDirected/有向无权有欧拉回路图.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        GraphDFSNoRecursionEulerLoopDirected noRecursionEulerLoopDirected = new GraphDFSNoRecursionEulerLoopDirected(graph, 0);
        loop = noRecursionEulerLoopDirected.getLoop();
        System.out.println(loop);
        System.out.println();

        // 3.非递归无欧拉回路
        filepath = "src/main/java/Chapter13DirectedGraph/Section05EulerLoopDirected/有向无权无欧拉回路图.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        noRecursionEulerLoopDirected = new GraphDFSNoRecursionEulerLoopDirected(graph, 0);
        loop = noRecursionEulerLoopDirected.getLoop();
        System.out.println(loop);
    }
}
/**
 * 顶点数V = 5, 边数E = 8
 * [0, 1, 2, 4, 3, 1, 3, 2, 0]
 *
 * 顶点数V = 5, 边数E = 5
 * []
 *
 * 顶点数V = 5, 边数E = 8
 * [0, 1, 2, 4, 3, 1, 3, 2, 0]
 *
 * 顶点数V = 5, 边数E = 5
 * []
 */