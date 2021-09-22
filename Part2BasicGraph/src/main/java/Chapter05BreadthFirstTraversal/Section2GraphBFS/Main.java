/***********************************************************
 * @Description : 测试BFS
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 08:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section2GraphBFS;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section2GraphBFS/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println("广度优先遍历顺序结果是：" + graphBFS.getOrderList());
    }
}

/**
 * 输出结果是：
 * 顶点数V = 7, 边数E = 7
 * 广度优先遍历顺序结果是：[0, 1, 2, 3, 4, 6, 5]
 */
