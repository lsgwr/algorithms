/***********************************************************
 * @Description : 广度优先遍历解决单源路径问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 16:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section3GraphBFSSingleSourcePath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter05BreadthFirstTraversal.Section2GraphBFS.GraphBFS;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section2GraphBFS/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphBFSSingleSourcePath graphBFSSingleSourcePath = new GraphBFSSingleSourcePath(graph, 0);
        System.out.println("广度优先遍历顺序结果是：" + graphBFSSingleSourcePath.getOrderList());
        System.out.println("0 -> 3 : " + graphBFSSingleSourcePath.path(3));
        System.out.println("0 -> 6 : " + graphBFSSingleSourcePath.path(6));
    }
}

/**
 * 输出结果是：
 * <p>
 * 顶点数V = 7, 边数E = 7
 * 广度优先遍历顺序结果是：[0, 1, 3, 2, 6, 5, 4]
 * 0 -> 3 : [0, 1, 3]
 * 0 -> 6 : [0, 2, 6]]
 */
