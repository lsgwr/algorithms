/***********************************************************
 * @Description : 最大匹配问题测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 14:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section1to3Matching;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        BipartiteMaxMatching maxMatching = new BipartiteMaxMatching(graph);
        System.out.println("最大匹配对数：" + maxMatching.getMaxMatch());
        System.out.println();

        filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph2.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        maxMatching = new BipartiteMaxMatching(graph);
        System.out.println("最大匹配对数：" + maxMatching.getMaxMatch());
    }
}
/**
 * 顶点数V = 8, 边数E = 6
 * 最大匹配对数：3
 *
 * 顶点数V = 8, 边数E = 7
 * 最大匹配对数：4
 */