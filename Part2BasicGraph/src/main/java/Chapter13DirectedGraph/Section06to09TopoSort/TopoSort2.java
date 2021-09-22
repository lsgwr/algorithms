/***********************************************************
 * @Description : 基于DFS后序遍历的拓扑排序，只能处理有向无环图，
 *                不能检测环，所以实用价值不大，但是对理解强联通分量很有用处
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/26 0:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section06to09TopoSort;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;
import Chapter03DepthFirstTraversal.GraphDFSPostOrder;

import java.util.Collections;
import java.util.List;

public class TopoSort2 {
    public static void main(String[] args) {
        // 1.无环，可以进行拓扑排序
        String filepath = "src/main/java/Chapter13DirectedGraph/Section06to09TopoSort/有向无权无环图.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        GraphDFSPostOrder postOrder = new GraphDFSPostOrder(graph);
        List<Integer> orderList = (List<Integer>) postOrder.getOrderList();
        Collections.reverse(orderList);
        System.out.print("拓扑排序结果为：");
        System.out.println(orderList);
    }
}
/**
 * 顶点数V = 5, 边数E = 5
 * 拓扑排序结果为：[0, 1, 3, 2, 4]
 */