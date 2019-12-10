/***********************************************************
 * @Description : 连通分量的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-07 22:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section3IsConnected;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Main {

    private static void printConnectedComponentsMap(Map<Integer, List<Integer>> connectedComponentsMap) {
        for (Integer ccid : connectedComponentsMap.keySet()) {
            System.out.print("|----连通分量" + ccid + "包含的顶点:");
            List<Integer> vertexList = connectedComponentsMap.get(ccid);
            for (Integer vertex : vertexList) {
                System.out.print(vertex + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 连通图
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFS4IsConnected graphDFS1 = new GraphDFS4IsConnected(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS1.getOrderList());
        System.out.println("已访问数组visited[]是：" + Arrays.toString(graphDFS1.getVisited()));
        System.out.println("连通分量的个数是：" + graphDFS1.getConnectedComponentCount());
        System.out.println("连通分量的详情是(每个连通分量里有哪些顶点)：");
        Map<Integer, List<Integer>> connectedComponentsMap = graphDFS1.getConnectedComponentsMap();
        printConnectedComponentsMap(connectedComponentsMap);
        // 5和6在一个连通分量中，是可连通地
        System.out.println("顶点5和6是否相连：" + graphDFS1.isConnected(5, 6));

        System.out.println();

        // 非连通图
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        GraphDFS4IsConnected graphDFS2 = new GraphDFS4IsConnected(graph);
        System.out.println("深度优先遍历的结果是：" + graphDFS2.getOrderList());
        System.out.println("已访问数组visited[]是：" + Arrays.toString(graphDFS2.getVisited()));
        System.out.println("连通分量的个数是：" + graphDFS2.getConnectedComponentCount());
        System.out.println("连通分量的详情是(每个连通分量里有哪些顶点)：");
        connectedComponentsMap = graphDFS2.getConnectedComponentsMap();
        printConnectedComponentsMap(connectedComponentsMap);
        // 5和6在两个连通分量中，是不可连通地
        System.out.println("顶点5和6是否相连：" + graphDFS2.isConnected(5, 6));
    }
}
