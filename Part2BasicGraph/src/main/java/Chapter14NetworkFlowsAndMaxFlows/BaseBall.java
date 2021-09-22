/***********************************************************
 * @Description : 最大流比赛问题之棒球比赛
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 21:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14NetworkFlowsAndMaxFlows;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.ReadWeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

public class BaseBall {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter14NetworkFlowsAndMaxFlows/baseball.txt";
        WeightedGraph networkGraph = new WeightedGraph(true);
        ReadWeightedGraph.init(networkGraph, filepath);
        MaxFlow maxFlow = new MaxFlow(networkGraph, 0, 10);
        // 结果为26，表明剩下的27场比赛没法让底特律以外的队伍都<=76，所以底特律没机会夺冠了，可以被淘汰了
        System.out.println("当前网络0->10的最大流为：" + maxFlow.getMaxFlow());
    }
}
/**
 * 顶点数V = 11, 边数E = 19
 * 当前网络0->10的最大流为：26
 */