/***********************************************************
 * @Description : 单源路径问题优化：DFS遍历到target就提前退出，这样可以极大地节省递归的成本
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-08 08:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section8SingleSourcePathOptimize;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.Arrays;


public class Main {
    private static void showResult(Graph graph, int source, int target) {
        GraphDFSSingleSourcePathOptimize singleSourcePathDFSOptimize = new GraphDFSSingleSourcePathOptimize(graph, source, target);
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathDFSOptimize.path());
        // 因为优化后每个GraphDFSSingleSourcePathOptimize只是为了指定的source和target，并没有访问全部的节点,所以可以看到节点部分为true，部分为false
        System.out.println("节点是否访问的情况是(每个数组下标代表一个定点)：" + Arrays.toString(singleSourcePathDFSOptimize.getVisited()));
    }

    public static void main(String[] args) {
        // 求起点source到目标点target的单源路径(source就会作为DFS的起点)
        int source = 0;
        int target = 6;
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
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
 * 结果如下：
 * 顶点数V = 7, 边数E = 6
 * 0--->6的路径:[0, 1, 3, 2, 6]
 * 节点是否访问的情况是(每个数组下标代表一个元素)：[true, true, true, true, false, false, true]
 * 0--->5的路径:[]
 * 节点是否访问的情况是(每个数组下标代表一个元素)：[true, true, true, true, true, false, true]
 * 0--->1的路径:[0, 1]
 * 节点是否访问的情况是(每个数组下标代表一个元素)：[true, true, false, false, false, false, false]
 */
