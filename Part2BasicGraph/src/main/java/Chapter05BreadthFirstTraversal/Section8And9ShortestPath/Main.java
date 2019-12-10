package Chapter05BreadthFirstTraversal.Section8And9ShortestPath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.Arrays;

public class Main {

    private static void showResult(Graph graph, int source, int target) {
        // BFS的单源路径
        GraphBFSUnweightedGraphShortestPath unweightedGraphShortestPath = new GraphBFSUnweightedGraphShortestPath(graph, source, target);
        System.out.println(source + "--->" + target + "的最短路径:" + unweightedGraphShortestPath.path());
        // 因为优化后每个GraphBFSSingleSourcePathOptimize只是为了指定的source和target，并没有访问全部的节点,所以可以看到节点部分为true，部分为false
        System.out.println("节点是否访问的情况是(每个数组下标代表一个定点)：" + Arrays.toString(unweightedGraphShortestPath.getVisited()));
        System.out.println("0-->6的最短路径值时：" + unweightedGraphShortestPath.shortestDistance());
        System.out.println();
    }

    public static void main(String[] args) {
        // 求起点source到目标点target的单源路径(source就会作为DFS的起点)
        int source = 0;
        int target = 6;
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);

        // BFS的单源路径
        showResult(graph, source, target);

        source = 1;
        target = 3;
        showResult(graph, source, target);

        source = 0;
        target = 4;
        showResult(graph, source, target);
    }
}

/**
 * 输出结果是：
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 0--->6的最短路径:[0, 2, 6]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, true, false, true]
 * 0-->6的最短路径值时：2
 * <p>
 * 1--->3的最短路径:[1, 3]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, false, true, false, false, false]
 * 0-->6的最短路径值时：1
 * <p>
 * 0--->4的最短路径:[0, 1, 4]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, true, false, false]
 * 0-->6的最短路径值时：2
 */
