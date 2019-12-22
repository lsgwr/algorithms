/***********************************************************
 * @Description : 欧拉回路测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 10:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10EulerLoopAndEulerPath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter10EulerLoopAndEulerPath/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.println(new EulerLoop(graph).dfs());
    }
}
/**
 * 顶点数V = 5, 边数E = 6
 * [0, 1, 2, 3, 4, 2, 0]
 */
