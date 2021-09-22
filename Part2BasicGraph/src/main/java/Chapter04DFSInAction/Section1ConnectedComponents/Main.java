/***********************************************************
 * @Description : 连通分量的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-07 22:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section1ConnectedComponents;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFS4ConnectedComponents graphDFS1 = new GraphDFS4ConnectedComponents(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS1.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS1.getConnectedComponentCount());

        System.out.println();

        // 非连通图
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFS4ConnectedComponents graphDFS2 = new GraphDFS4ConnectedComponents(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS2.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS2.getConnectedComponentCount());
    }
}
/**
 * 顶点数V = 7, 边数E = 8
 * 深度优先遍历的结果是：[0, 1, 3, 2, 6, 5, 4]
 * 连通分量的个数是：1
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 深度优先遍历的结果是：[0, 1, 3, 2, 6, 4, 5]
 * 连通分量的个数是：2
 */