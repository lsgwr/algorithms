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

public class Main {
    public static void main(String[] args) {
        // 1.无向图测试
        String filepath = "src/main/java/Chapter13DirectedGraph/Section01BasicTest/无权图.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filepath);
        System.out.println(graph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(graph);
        System.out.println();

        // 2.有向图测试
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        System.out.println(graph.isDirected()?"该图是有向图":"该图是无向图");
        System.out.println(graph);
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
 */