/***********************************************************
 * @Description : 测试匈牙利算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/28 1:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section5to7Hungarian;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 1.基于BFS的匈牙利算法
        String filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph.txt";
        Graph graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        HungarianBFS maxMatchingBFS = new HungarianBFS(graph);
        System.out.println("最大匹配对数：" + maxMatchingBFS.getMaxMatch());
        System.out.println();

        filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph2.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        maxMatchingBFS = new HungarianBFS(graph);
        System.out.println("最大匹配对数：" + maxMatchingBFS.getMaxMatch());
        System.out.println();

        // 2.基于DFS的匈牙利算法
        filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        HungarianDFS maxMatchingDFS = new HungarianDFS(graph);
        System.out.println("最大匹配对数：" + maxMatchingDFS.getMaxMatch());
        System.out.println();

        filepath = "src/main/java/Chapter15Matching/Section1to3Matching/graph2.txt";
        graph = new Graph(true);
        ReadGraph.init(graph, filepath);
        maxMatchingDFS = new HungarianDFS(graph);
        System.out.println("最大匹配对数：" + maxMatchingDFS.getMaxMatch());
    }
}
/**
 * 顶点数V = 8, 边数E = 6
 * 最大匹配对数：3
 *
 * 顶点数V = 8, 边数E = 7
 * 最大匹配对数：4
 */