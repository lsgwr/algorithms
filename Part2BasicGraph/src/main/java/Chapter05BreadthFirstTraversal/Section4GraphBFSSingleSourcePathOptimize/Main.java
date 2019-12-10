/***********************************************************
 * @Description : 广度优先遍历解决单源路径问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 16:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section4GraphBFSSingleSourcePathOptimize;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.Arrays;

public class Main {
    private static void showResult(Graph graph, int source, int target) {
        GraphBFSSingleSourcePathOptimize singleSourcePathBFSOptimize = new GraphBFSSingleSourcePathOptimize(graph, source, target);
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathBFSOptimize.path());
        // 因为优化后每个GraphBFSSingleSourcePathOptimize只是为了指定的source和target，并没有访问全部的节点,所以可以看到节点部分为true，部分为false
        System.out.println("节点是否访问的情况是(每个数组下标代表一个定点)：" + Arrays.toString(singleSourcePathBFSOptimize.getVisited()));
    }

    public static void main(String[] args) {
        // 求起点source到目标点target的单源路径(source就会作为BFS的起点)
        int source = 0;
        int target = 6;
        String filePath = "src/main/java/Chapter05BreadthFirstTraversal/Section2GraphBFS/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        showResult(graph, source, target);
        // 5和0不在一个连通分量内，Section3有验证，此时路径为[]，表示无路径，所以这个方法也可以用于判断连通性，和并查集一起思考
        target = 5;
        showResult(graph, source, target);
        // 1和0是在一个连通分量内的
        target = 1;
        showResult(graph, source, target);
    }
}

/**
 * 输出结果是：
 * <p>
 * 顶点数V = 7, 边数E = 7
 * 0--->6的路径:[0, 2, 6]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, true, false, true]
 * 0--->5的路径:[0, 2, 6, 5]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, true, true, true, true, true]
 * 0--->1的路径:[0, 1]
 * 节点是否访问的情况是(每个数组下标代表一个定点)：[true, true, false, false, false, false, false]
 */
