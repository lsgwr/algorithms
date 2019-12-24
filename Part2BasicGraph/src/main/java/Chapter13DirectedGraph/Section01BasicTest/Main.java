/***********************************************************
 * @Description : 测试修改后支持有向图的Graph类
 * Graph类修改的地方：添加边和删除边的时候对是否有向做一下预判断
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/23 17:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section01BasicTest;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        // 1.无向无权图测试
        String filepath = "src/main/java/Chapter13DirectedGraph/Section01BasicTest/无权图.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filepath);
        System.out.println(graph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(graph);
        System.out.println();

        // 2.有向无权图测试
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        System.out.println(graph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(graph);
        System.out.println();

        // 3.无向右权图测试
        filepath = "src/main/java/Chapter13DirectedGraph/Section01BasicTest/有权图.txt";
        WeightedGraph weightedGraph= new WeightedGraph(false);
        ReadWeightedGraph.init(weightedGraph, filepath);
        System.out.println(weightedGraph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(weightedGraph);
        System.out.println();

        // 2.有向无权图测试
        weightedGraph = new WeightedGraph(true);
        ReadWeightedGraph.init(weightedGraph, filepath);
        System.out.println(weightedGraph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(weightedGraph);
        System.out.println();
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 该图是无向图
 * 顶点数V = 5, 边数E = 5
 * vertex 0:	1
 * vertex 1:	0	2	3
 * vertex 2:	1	3	4
 * vertex 3:	1	2
 * vertex 4:	2
 *
 *
 * 顶点数V = 5, 边数E = 5
 * 该图是有向图
 * 顶点数V = 5, 边数E = 5
 * vertex 0:	1
 * vertex 1:	2	3
 * vertex 2:	4
 * vertex 3:	2
 * vertex 4:
 *
 *
 * 顶点数V = 5, 边数E = 8
 * 该图是无向图
 * 顶点数V = 5, 边数E = 8，完整的邻接表信息如下：
 * v=0: (w=1, weight=4) (w=2, weight=2)
 * v=1: (w=0, weight=4) (w=2, weight=1) (w=3, weight=2) (w=4, weight=3)
 * v=2: (w=0, weight=2) (w=1, weight=1) (w=3, weight=4) (w=4, weight=5)
 * v=3: (w=1, weight=2) (w=2, weight=4) (w=4, weight=1)
 * v=4: (w=1, weight=3) (w=2, weight=5) (w=3, weight=1)
 *
 *
 * 顶点数V = 5, 边数E = 8
 * 该图是有向图
 * 顶点数V = 5, 边数E = 8，完整的邻接表信息如下：
 * v=0: (w=1, weight=4) (w=2, weight=2)
 * v=1: (w=2, weight=1) (w=3, weight=2) (w=4, weight=3)
 * v=2: (w=3, weight=4) (w=4, weight=5)
 * v=3: (w=4, weight=1)
 * v=4:
 */