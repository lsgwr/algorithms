/***********************************************************
 * @Description : 利用图的广度优先遍历(BFS)实现求解无向图的最短路径问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-10 11:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section8And9ShortestPath;

import Chapter02GraphExpress.Graph;

import java.util.*;

public class GraphBFSUnweightedGraphShortestPath {

    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不像DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 单源路径的起点、目标点
     */
    private int source, target;

    /**
     * pre数组是为了记录起点source到目标点target的路径(中间经过了哪些点)
     */
    private int[] pre;

    /**
     * 每个顶点到source的距离
     */
    private int[] distance;

    public GraphBFSUnweightedGraphShortestPath(Graph graph, int source, int target) {
        graph.validateVertex(source);
        graph.validateVertex(target);
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.visited = new boolean[graph.V()];
        // 给存储单源路径的数组赋值
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        distance = new int[graph.V()];
        Arrays.fill(distance, -1);
        // 因为单源路径问题是和连通分量无关的，所以dfs()要用最早没有考虑连通分量的那版
        bfs();
    }

    /**
     * 从source点开始进行广度优先遍历
     */
    private void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source;
        // 起点到起点的距离为0
        distance[source] = 0;

        if (source == target) {
            return;
        }
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    distance[w] = distance[v] + 1;
                    if (w == target) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * 判断图的遍历起点是否和target点连通，实际只需要看下visit[v]是否为true即可，为true表示在一个连通分量上，肯定是连通地
     *
     * @return 判断图的遍历起点是否和target点连通
     */
    public boolean isConnected() {
        graph.validateVertex(target);
        return visited[target];
    }

    public boolean[] getVisited() {
        return visited;
    }

    /**
     * 找到起点source到目标定点target的路径(实际也是source到target的最短路径)
     *
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<Integer> path() {
        List<Integer> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isConnected()) {
            // 用pre数组从target一直找到source点，记录下中间经过的所有点，就是要求的单源路径
            int current = target;
            while (current != source) {
                pathList.add(current);
                current = pre[current];
            }
            // 起点要加上
            pathList.add(source);
            // 因为是从source到target的路径，所以要颠倒下
            Collections.reverse(pathList);
            return pathList;
        } else {
            // 没有路径就直接返回空集合
            return pathList;
        }
    }

    /**
     * 从source到target的最短路径值
     */
    public int shortestDistance() {
        return distance[target];
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }
}
