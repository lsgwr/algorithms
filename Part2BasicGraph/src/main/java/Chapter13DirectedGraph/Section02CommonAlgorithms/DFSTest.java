/***********************************************************
 * @Description : DFS在有向图中的测试,DFS直接使用无向图的即可，完全不用改
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/24 19:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section02CommonAlgorithms;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter03DepthFirstTraversal.GraphDFS;
import Chapter03DepthFirstTraversal.GraphDFSPostOrder;

public class DFSTest {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter13DirectedGraph/Section01BasicTest/无权图.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        System.out.println(graph.isDirected() ? "该图是有向图" : "该图是无向图");
        System.out.print("前序遍历：");
        System.out.println(new GraphDFS(graph).getOrderList());
        System.out.print("后序遍历：");
        System.out.println(new GraphDFSPostOrder(graph).getOrderList());
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 该图是有向图
 * 前序遍历：[0, 1, 2, 4, 3]
 * 后序遍历：[4, 2, 3, 1, 0]
 */