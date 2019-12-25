/***********************************************************
 * @Description : 拓扑排序测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/26 0:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section06to09TopoSort;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1.无环，可以进行拓扑排序
        String filepath = "src/main/java/Chapter13DirectedGraph/Section06to09TopoSort/有向无权无环图.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        TopoSort topoSort = new TopoSort(graph);
        List<Integer> topoOrder = topoSort.getTopoOrder();
        System.out.print("拓扑排序结果为：");
        System.out.println(topoOrder);
        System.out.println(topoSort.hasCycle()?"图中有环":"图中无环");
        System.out.println();

        // 2.有环，无法进行拓扑排序
        filepath = "src/main/java/Chapter13DirectedGraph/Section06to09TopoSort/有向无权有环图.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        topoSort = new TopoSort(graph);
        topoOrder = topoSort.getTopoOrder();
        System.out.print("拓扑排序结果为：");
        System.out.println(topoOrder);
        System.out.println(topoSort.hasCycle()?"图中有环":"图中无环");
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 拓扑排序结果为：[0, 1, 3, 2, 4]
 * 图中无环
 *
 * 顶点数V = 5, 边数E = 5
 * 拓扑排序结果为：[]
 * 图中有环
 */