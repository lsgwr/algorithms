/***********************************************************
 * @Description : 哈密尔顿路径测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/20 23:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section5HamiltonPath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter09HamiltonLoop.Section1to4HamiltonLoop.GraphDFSHamiltonLoop;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter09HamiltonLoop/Section5HamiltonPath/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("起始点为0时哈密尔顿路径详情为：");
        List<Integer> path = new GraphDFSHamiltonPath(graph, 0).getPath();
        System.out.println(path);

        path = new GraphDFSHamiltonPath(graph, 1).getPath();
        System.out.print("起始点为1时哈密尔顿路径详情为：");
        System.out.println(path);
    }
}
/**
 * 顶点数V = 4, 边数E = 4
 * 起始点为0时哈密尔顿路径详情为：[0, 2, 1, 3]
 * 起始点为1时哈密尔顿路径详情为：[]
 */