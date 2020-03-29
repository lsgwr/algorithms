/***********************************************************
 * @Description : 第5节_最短路径之Dijkstra算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/28 22:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_贪心算法.第5节_最短路径之Dijkstra算法;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 读入并初始化好图
        Scanner scanner = new Scanner(System.in);
        // 注意要从图的输入判断下是有向图还是无向图，本题显然是有向图
        WeightedGraph graph = new WeightedGraph(true);
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
        // 4.读入起点，即减去1
        int start = scanner.nextInt() - 1;
        // 5.调用Dijkstra获取起点到各个顶点的最短路径
        ShortestPathDijkstra dijkstra = new ShortestPathDijkstra(graph, start);
        for (int v = 0; v < graph.V(); v++) {
            System.out.println(start + "到" + v + "的最短距离为：" + dijkstra.shortestDistanceTo(v) + ", 路径详情是：" + dijkstra.getPath(v));
        }
    }
}
/**
 * 读入：从in.txt中输入
 *
 * 输出：
 *
 * 4到0的最短距离为：8, 路径详情是：[4, 0]
 * 4到1的最短距离为：24, 路径详情是：[4, 0, 1]
 * 4到2的最短距离为：23, 路径详情是：[4, 0, 2]
 * 4到3的最短距离为：30, 路径详情是：[4, 0, 2, 3]
 * 4到4的最短距离为：0, 路径详情是：[4]
 */

