/***********************************************************
 * @Description : 深度优先遍历的测试代码
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03DepthFirstTraversal;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFS(graph).getOrderList());

        // 连通图,后序遍历
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFSPostOrder(graph).getOrderList());

        // DFS的非递归实现
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFSNoRecursion(graph).getOrderList());

        // 非连通图，用不支持非联通图的DFS
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFSNoCC(graph).getOrderList());
        // 非连通图，用支持非联通图的DFS
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFS(graph).getOrderList());
    }
}
/**
 * 顶点数V = 7, 边数E = 8
 * [0, 1, 3, 2, 6, 5, 4]
 * 顶点数V = 7, 边数E = 8
 * [5, 6, 2, 3, 4, 1, 0]
 * 顶点数V = 7, 边数E = 8
 * [0, 1, 2, 3, 6, 5, 4]
 * 顶点数V = 7, 边数E = 6
 * [0, 1, 3, 2, 6, 4]
 * 顶点数V = 7, 边数E = 6
 * [0, 1, 3, 2, 6, 4, 5]
 */