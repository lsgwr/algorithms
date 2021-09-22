/***********************************************************
 * @Description : 无向有权图基于Prim算法求最小生成树问题
 * 参考 https://gitee.com/lsgwr/algorithms/blob/master/Part2BasicGraph/第11章_无向有权图之最小生成树问题.md
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 10:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_贪心算法.第7节_最小生成树之Prim算法;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 读入并初始化好图
        Scanner scanner = new Scanner(System.in);
        // 注意要从图的输入判断下是有向图还是无向图，本题显然是有向图
        WeightedGraph graph = new WeightedGraph(false);
        // 1.顶点数
        int V = scanner.nextInt();
        if (V < 0) {
            return;
        }
        graph.setVertices(V);
        // 2.边数
        int E = scanner.nextInt();
        if (E < 0) {
            return;
        }
        // 3.读取边的顶点和权值
        for (int i = 0; i < E; i++) {
            int v = scanner.nextInt();
            assert v >= 0 && v < V;
            int w = scanner.nextInt();
            assert w >= 0 && w < V;
            int weight = scanner.nextInt();
            if (v == w) {
                throw new IllegalArgumentException("Self Loop is Detected!");
            }
            // 注意顶点编号从1开始，所以传进去需要减1
            graph.addEdge(v - 1, w - 1, weight);
        }
        // 4.求最小生成
        MinimumSpanningTreePrim prim = new MinimumSpanningTreePrim(graph);
        List<WeightedEdge> edges = prim.getMst();
        System.out.print("最短路径为：");
        int min = 0;
        for (WeightedEdge edge : edges) {
            min+=edge.getWeight();
        }
        System.out.println(min);
        System.out.print("详细的路径信息为：");
        System.out.println(edges);
    }
}
/**
 * 输入：in.txt
 *
 * 输出：
 * 最短路径为：57
 * 详细的路径信息为：[(0-1: 23), (1-6: 1), (6-2: 4), (6-3: 9), (3-4: 3), (4-5: 17)]
 */
