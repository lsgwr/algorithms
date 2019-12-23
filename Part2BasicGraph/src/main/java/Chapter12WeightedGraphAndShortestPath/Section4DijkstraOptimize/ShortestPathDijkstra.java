/***********************************************************
 * @Description : 无向有权图的最短路径算法之Dijkstra算法，时间复杂度为O(ElogE)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/23 17:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12WeightedGraphAndShortestPath.Section4DijkstraOptimize;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.*;

public class ShortestPathDijkstra {
    /**
     * 无向有权图
     */
    private WeightedGraph graph;
    /**
     * 求最短路径的起点，用户指定
     */
    private int start;
    /**
     * 各个定点到起始点的距离
     */
    private int[] distances;

    /**
     * 是否找到了顶点到起始点的最小距离值
     */
    private boolean[] findShortest;

    /**
     * 记录访问顺序的数组
     */
    private int[] pre;

    /**
     * 存储顶点和顶点到起始点start的最小距离值(临时和最终的)
     */
    private class Node implements Comparable<Node> {
        /**
         * 节点编号
         */
        private int v;
        /**
         * 节点v到起始点start的最小距离值(临时或最终的)
         */
        private int distance;

        public Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }

        public int getV() {
            return v;
        }

        public int getDistance() {
            return distance;
        }

        public void setV(int v) {
            this.v = v;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Node that) {
            return this.distance - that.distance;
        }
    }

    public ShortestPathDijkstra(WeightedGraph graph, int start) {
        graph.validateVertex(start);
        this.graph = graph;
        this.start = start;

        distances = new int[graph.V()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // 初始化访问顺序数组
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        // 起始点到起始点的最短距离为0
        distances[start] = 0;
        // 起始点的上一个访问节点认为是自己
        pre[start] = start;
        // 初始化所有的节点都没找到最短路径
        findShortest = new boolean[graph.V()];
        Arrays.fill(findShortest, false);
        dijkstra();
    }

    public void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            /**
             * 步骤1：确认一个顶点的距离值为到顶点的最小距离值
             */
            // 复杂度是log级别的
            int curV = pq.remove().getV();
            // 因为一个节点可能因为更新最小距离值而被加入多次，所以pq弹出节点可能弹出已经确定最小值的点，
            // 因此我们需要判断下，是地话就直接跳过
            if (findShortest[curV]) {
                continue;
            }
            // 0到curV的最短路径就确定了
            findShortest[curV] = true;

            /**
             * 步骤2：根据上面确认的最小距离值的顶点，更新起始点到其邻接点的距离值
             */
            for (int w : graph.adj(curV)) {
                if (!findShortest[w]) {
                    if (distances[curV] + graph.getWeight(curV, w) < distances[w]) {
                        distances[w] = distances[curV] + graph.getWeight(curV, w);
                        // 可能一个顶点会被加入多次，但是不影响，因为每次pq取出地都是最小值，
                        // 而我们每次加入地一个新的节点的重复值只会更小，所以取最小值的时候一定能取到我们新加入地值
                        pq.add(new Node(w, distances[w]));
                        pre[w] = curV;
                    }
                }
            }
        }
    }

    /**
     * 获取起始点start到顶点v的最小距离值
     */
    public int[] getDistances() {
        return distances;
    }

    /**
     * 判断顶点v到定点start之间是否连通
     */
    public boolean isConnectedTo(int v) {
        graph.validateVertex(v);
        return findShortest[v];
    }

    /**
     * 起点start到定点v的最小距离值
     */
    public int shortestDistanceTo(int v) {
        graph.validateVertex(v);
        return distances[v];
    }

    /**
     * 获取起始点到顶点v的最短路劲轨迹
     */
    public Iterable<Integer> getPath(int v) {
        List<Integer> path = new ArrayList<>();
        if (!isConnectedTo(v)) {
            return path;
        }
        int cur = v;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        // 加入起始点
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
