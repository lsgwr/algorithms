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
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        System.out.println(new GraphDFS(graph).getOrderList());
    }
}
