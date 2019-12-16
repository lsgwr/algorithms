/***********************************************************
 * @Description : 图的广度优先遍历中的环检测
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 19:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section7BiPartitionDetect;

import Chapter02GraphExpress.Graph;

import java.util.*;

/**
 * 二分图染色过程中用到的颜色枚举,用于给定点染色
 */
enum VertexColor {
    BLUE(0, "蓝色"), GREEN(1, "绿色");

    VertexColor(int num, String color) {
        this.num = num;
        this.color = color;
    }

    private int num;
    private String color;

    public int getNum() {
        return num;
    }
}

public class GraphBFSBiPartitionDetect {

    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不想DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 颜色数组，存储每个节点的颜色
     */
    private int[] colors;

    /**
     * 是否是二分图,默认成是二分图
     */
    private boolean biPartition = true;

    public GraphBFSBiPartitionDetect(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.V()];
        // 初始化颜色数组
        this.colors = new int[graph.V()];
        Arrays.fill(this.colors, -1);
        // 从bfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用bfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    biPartition = false;
                    break;
                }
            }
        }
    }

    /**
     * 从source点开始进行广度优先遍历
     *
     * @param source 起点
     * @return
     */
    private boolean bfs(int source) {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://github.com/19920625lsg/algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        // 起始点染成蓝色，等效于colors[s] = 0;
        colors[source] = VertexColor.BLUE.getNum();
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w : graph.adj(v)) {
                // 遍历v的所有邻接点
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    // 颜色只有蓝(0)、绿(1)两种，w是v的邻接点，根据二分图的检测原理，w、v的颜色必须相反，只能一蓝一绿，蓝+绿 = 0 + 1 = 1,所以1-v的颜色 = 1-color = w的颜色
                    colors[w] = 1 - colors[v];
                } else if (colors[w] == colors[v]) {
                    // 如果w已经访问过，但是w作为v的邻接点和v的颜色相同，说明不是二分图
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 是否是二分图
     */
    public boolean isBiPartition() {
        return biPartition;
    }
}
