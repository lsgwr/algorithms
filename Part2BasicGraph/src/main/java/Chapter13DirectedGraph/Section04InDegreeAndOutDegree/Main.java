/***********************************************************
 * @Description : 入度和出度
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/25 16:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section04InDegreeAndOutDegree;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter13DirectedGraph/Section04InDegreeAndOutDegree/有向无权图.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        for (int v = 0; v < graph.V(); v++) {
            System.out.println(String.format("顶点%d的入度和出度：%d %d", v, graph.inDegree(v), graph.outDegree(v)));
        }
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 顶点0的入度和出度：0 1
 * 顶点1的入度和出度：1 2
 * 顶点2的入度和出度：2 1
 * 顶点3的入度和出度：1 1
 * 顶点4的入度和出度：1 0
 */