/***********************************************************
 * @Description : 连通分量的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-07 22:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section2ConnectedComponentsStatistic;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphDFS4ConnectedComponentsStatistic graphDFS1 = new GraphDFS4ConnectedComponentsStatistic(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS1.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS1.getConnectedComponentCount());

        // 非连通图
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphDFS4ConnectedComponentsStatistic graphDFS2 = new GraphDFS4ConnectedComponentsStatistic(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS2.getOrderList());
        System.out.println("连通分量的个数是：" + graphDFS2.getConnectedComponentCount());
    }
}
