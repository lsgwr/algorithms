/***********************************************************
 * @Description : 测试最大流算法Edmonds-Karp
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 7:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14NetworkFlowsAndMaxFlows;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter14NetworkFlowsAndMaxFlows/network.txt";
        WeightedGraph networkGraph = new WeightedGraph(true);
        ReadWeightedGraph.init(networkGraph, filepath);
        MaxFlow maxFlow = new MaxFlow(networkGraph, 0, 3);
        System.out.println("当前网络0->3的最大流为：" + maxFlow.getMaxFlow());
        for (int v = 0; v < networkGraph.V(); v++) {
            for (int w : networkGraph.adj(v)) {
                // 打印最大流下，每条边上经过的流量
                System.out.println(String.format("边顶点%d->顶点%d: 最大流时的流量%d;原图中容量限制%d", v, w, maxFlow.getFlow(v, w), networkGraph.getWeight(v, w)));
            }
        }
        System.out.println();

        filepath = "src/main/java/Chapter14NetworkFlowsAndMaxFlows/network2.txt";
        networkGraph = new WeightedGraph(true);
        ReadWeightedGraph.init(networkGraph, filepath);
        maxFlow = new MaxFlow(networkGraph, 0, 5);
        System.out.println("当前网络0->5的最大流为：" + maxFlow.getMaxFlow());
        for (int v = 0; v < networkGraph.V(); v++) {
            for (int w : networkGraph.adj(v)) {
                // 打印最大流下，每条边上经过的流量
                System.out.println(String.format("边顶点%d->顶点%d: 最大流时的流量%d;原图中容量限制%d", v, w, maxFlow.getFlow(v, w), networkGraph.getWeight(v, w)));
            }
        }
    }
}
/**
 * 顶点数V = 4, 边数E = 5
 * 当前网络0->3的最大流为：5
 * 边顶点0->顶点1: 最大流时的流量3;原图中容量限制3
 * 边顶点0->顶点2: 最大流时的流量2;原图中容量限制2
 * 边顶点1->顶点2: 最大流时的流量1;原图中容量限制5
 * 边顶点1->顶点3: 最大流时的流量2;原图中容量限制2
 * 边顶点2->顶点3: 最大流时的流量3;原图中容量限制3
 *
 * 顶点数V = 6, 边数E = 9
 * 当前网络0->5的最大流为：12
 * 边顶点0->顶点1: 最大流时的流量8;原图中容量限制9
 * 边顶点0->顶点3: 最大流时的流量4;原图中容量限制9
 * 边顶点1->顶点2: 最大流时的流量8;原图中容量限制8
 * 边顶点1->顶点3: 最大流时的流量0;原图中容量限制10
 * 边顶点2->顶点5: 最大流时的流量9;原图中容量限制10
 * 边顶点3->顶点2: 最大流时的流量1;原图中容量限制1
 * 边顶点3->顶点4: 最大流时的流量3;原图中容量限制3
 * 边顶点4->顶点2: 最大流时的流量0;原图中容量限制8
 * 边顶点4->顶点5: 最大流时的流量3;原图中容量限制7
 */