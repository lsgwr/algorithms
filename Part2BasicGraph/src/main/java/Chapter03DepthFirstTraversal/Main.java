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
        // 连通图
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFS(graph).getOrderList());

        // 非连通图
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFS(graph).getOrderList());
    }
}
