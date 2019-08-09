package Chapter05BreadthFirstTraversal.Section5GraphBFSConnectedComponents;

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
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphBFSConnectedComponents graphBFS1 = new GraphBFSConnectedComponents(graph);
        System.out.println("广度优先遍历的结果是：" + graphBFS1.getOrderList());
        System.out.println("已访问数组visited[]是：" + Arrays.toString(graphBFS1.getVisited()));
        System.out.println("连通分量的个数是：" + graphBFS1.getConnectedComponentCount());
        System.out.println("连通分量的详情是(每个连通分量里有哪些顶点)：");
        Map<Integer, List<Integer>> connectedComponentsMap = graphBFS1.getConnectedComponentsMap();
        printConnectedComponentsMap(connectedComponentsMap);

        System.out.println();

        // 非连通图
        filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphBFSConnectedComponents graphBFS2 = new GraphBFSConnectedComponents(graph);
        System.out.println("广度优先遍历的结果是：" + graphBFS2.getOrderList());
        System.out.println("已访问数组visited[]是：" + Arrays.toString(graphBFS2.getVisited()));
        System.out.println("连通分量的个数是：" + graphBFS2.getConnectedComponentCount());
        System.out.println("连通分量的详情是(每个连通分量里有哪些顶点)：");
        connectedComponentsMap = graphBFS2.getConnectedComponentsMap();
        printConnectedComponentsMap(connectedComponentsMap);
    }
}

/**
 * 输出结果是：
 * <p>
 * 顶点数V = 7, 边数E = 8
 * 广度优先遍历的结果是：[0, 1, 2, 3, 4, 6, 5]
 * 已访问数组visited[]是：[0, 0, 0, 0, 0, 0, 0]
 * 连通分量的个数是：1
 * 连通分量的详情是(每个连通分量里有哪些顶点)：
 * |----连通分量0包含的顶点:0 1 2 3 4 5 6
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 广度优先遍历的结果是：[0, 1, 2, 3, 4, 6, 5]
 * 已访问数组visited[]是：[0, 0, 0, 0, 0, 1, 0]
 * 连通分量的个数是：2
 * 连通分量的详情是(每个连通分量里有哪些顶点)：
 * |----连通分量0包含的顶点:0 1 2 3 4 6
 * |----连通分量1包含的顶点:5
 */
