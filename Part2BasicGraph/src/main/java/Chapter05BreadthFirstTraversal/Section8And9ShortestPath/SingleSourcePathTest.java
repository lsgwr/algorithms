package Chapter05BreadthFirstTraversal.Section8And9ShortestPath;


import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter04DFSInAction.Section8SingleSourcePathOptimize.GraphDFSSingleSourcePathOptimize;
import Chapter05BreadthFirstTraversal.Section4GraphBFSSingleSourcePathOptimize.GraphBFSSingleSourcePathOptimize;

import java.util.Arrays;

public class SingleSourcePathTest {
    public static void main(String[] args) {
        // 求起点source到目标点target的单源路径(source就会作为DFS的起点)
        int source = 0;
        int target = 6;
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);

        // DFS的单源路径
        GraphDFSSingleSourcePathOptimize singleSourcePathDFSOptimize = new GraphDFSSingleSourcePathOptimize(graph, source, target);
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathDFSOptimize.path());
        // 因为优化后每个GraphDFSSingleSourcePathOptimize只是为了指定的source和target，并没有访问全部的节点,所以可以看到节点部分为true，部分为false
        System.out.println("节点是否访问的情况是(每个数组下标代表一个定点)：" + Arrays.toString(singleSourcePathDFSOptimize.getVisited()));

        // BFS的单源路径
        GraphBFSSingleSourcePathOptimize singleSourcePathBFSOptimize = new GraphBFSSingleSourcePathOptimize(graph, source, target);
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathBFSOptimize.path());
        // 因为优化后每个GraphBFSSingleSourcePathOptimize只是为了指定的source和target，并没有访问全部的节点,所以可以看到节点部分为true，部分为false
        System.out.println("节点是否访问的情况是(每个数组下标代表一个定点)：" + Arrays.toString(singleSourcePathBFSOptimize.getVisited()));
    }
}

/**
 * 结果如下，可以看出BFS的单源路径问题实际就是求出了source到target得最短路径
 * <p>
 * 顶点数V = 7, 边数E = 6
 * 0--->6的路径:[0, 1, 3, 2, 6]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, false, false, true]
 * 0--->6的路径:[0, 2, 6]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, true, false, true]
 *
 * 图片见 https://img.mukewang.com/szimg/5d4e2d9500015f8617281080.jpg
 */
