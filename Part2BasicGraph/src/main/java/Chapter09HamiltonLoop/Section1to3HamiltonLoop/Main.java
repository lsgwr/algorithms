/***********************************************************
 * @Description : 哈密尔顿回路测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-20 14:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section1to3HamiltonLoop;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 连通图,前序遍历
        String filePath = "src/main/java/Chapter09HamiltonLoop/Section1to3HamiltonLoop/graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        System.out.print("哈密尔顿路径详情为：");
        List<Integer> path = new GraphDFSHamiltonLoop(graph).getPath();
        System.out.println(path);
        System.out.println();

        filePath = "src/main/java/Chapter09HamiltonLoop/Section1to3HamiltonLoop/graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filePath);
        path = new GraphDFSHamiltonLoop(graph).getPath();
        System.out.println(path);
    }
}
