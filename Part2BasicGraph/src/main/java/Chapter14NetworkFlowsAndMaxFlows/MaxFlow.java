/***********************************************************
 * @Description : 网络最大流问题(基于多次BFS进行查找)的Edmonds-Karp算法
 * bfs以及增广路径的求解参考Chapter05BreadthFirstTraversal.Section3GraphBFSSingleSourcePath.GraphBFSSingleSourcePath.java
 * 时间复杂度为O(V*E*E)，是个多项式级别的算法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/26 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14NetworkFlowsAndMaxFlows;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.*;

public class MaxFlow {
    /**
     * 有向有权图作为网络
     */
    private WeightedGraph networkGraph;

    /**
     * 残量图
     */
    private WeightedGraph residualGraph;
    /**
     * 源点s
     */
    private int source;
    /**
     * 汇点t
     */
    private int target;
    /**
     * 最大流的值
     */
    private int maxFlow;

    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 记录上一个访问顶点
     */
    private int[] pre;

    public MaxFlow(WeightedGraph networkGraph, int source, int target) {
        if (!networkGraph.isDirected()) {
            throw new IllegalArgumentException("最大流算法只支持有向图！");
        }
        if (networkGraph.V() < 2) {
            throw new IllegalArgumentException("网络流问题的图应该至少2条边!");
        }
        networkGraph.validateVertex(source);
        networkGraph.validateVertex(target);
        if (source == target) {
            throw new IllegalArgumentException("源点和汇点必须是不同的图");
        }
        this.networkGraph = networkGraph;
        this.source = source;
        this.target = target;

        this.residualGraph = new WeightedGraph(networkGraph.V(), true);

        edmondsKarp();
    }

    /**
     * 求最大流的Edmonds-Karp算法
     */
    public void edmondsKarp() {
        for (int v = 0; v < networkGraph.V(); v++) {
            for (int w : networkGraph.adj(v)) {
                // 边的权值c
                int capacity = networkGraph.getWeight(v, w);
                // 初始化残量图
                residualGraph.addEdge(v, w, capacity);
                residualGraph.addEdge(w, v, 0);
            }
        }
        // 不断进行bfs寻找增广路径
        while (true) {
            List<Integer> augPath = getAugmentingPathByBfs();
            if (augPath.size() == 0) {
                break;
            }
            // 增广路径上的流量f
            int flow = Integer.MAX_VALUE;
            // 计算增广路径的权值最小值，作为增广路径的流量
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                // 不断更新本次增广路径上的最小流量值
                flow = Math.min(flow, residualGraph.getWeight(v, w));
            }
            // 不断累积每条增广路径的流量，最终找完所有的增广路径就得到了最终的网络最大流
            maxFlow += flow;
            // Todo:根据增广路径更新残量图residualGraph
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                // 正向边 新的权值 = 原容量-流出的流量
                residualGraph.setWeight(v, w, residualGraph.getWeight(v, w) - flow);
                // 更新反向边的权值.。反向流中增加的流量为f
                residualGraph.setWeight(w, v, residualGraph.getWeight(w, v) + flow);
            }
        }
    }

    /**
     * 获取增广路径上所有顶点的集合，实际就是广度优先遍历
     */
    private List<Integer> getAugmentingPathByBfs() {
        // 残量网络进行bfs需要的变量，每次获取增广路径都要进行一次bfs，所以每次getAugmentingPathByBfs()都需要初始化visited和pre数组
        this.visited = new boolean[residualGraph.V()];
        // 给存储单源路径的数组赋值
        pre = new int[residualGraph.V()];
        Arrays.fill(pre, -1);

        // 开始bfs获取增广路径
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        // 起点的上一个节点可以认为是自己
        pre[source] = source;
        // 在残量网络中进行bfs
        while (!queue.isEmpty()) {
            int v = queue.remove();
            if (v == target) {
                // 到达汇点就可以退出了
                break;
            }
            for (int w : residualGraph.adj(v)) {
                // 顶点没被访问而且边v->w的权值不为0
                if (!visited[w] && residualGraph.getWeight(v, w) > 0) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
        List<Integer> augPath = new ArrayList<>();
        if (!visited[target]) {
            return augPath;
        } else {
            // 找到了源点source到汇点target增广路径
            augPath = (List<Integer>) path(target);
        }
        return augPath;
    }

    /**
     * 判断图的遍历起点是否和target点连通，实际只需要看下visit[v]是否为true即可，为true表示在一个连通分量上，肯定是连通地
     *
     * @param target 目标定点索引
     * @return 判断图的遍历起点是否和target点连通
     */
    public boolean isSourceConnectedTo(int target) {
        networkGraph.validateVertex(target);
        return visited[target];
    }

    /**
     * 找到起点source到目标定点target的路径
     *
     * @param target 目标定点
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<Integer> path(int target) {
        List<Integer> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isSourceConnectedTo(target)) {
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
     * 获取最大流
     */
    public int getMaxFlow() {
        return maxFlow;
    }

    /**
     * 获取v->w之间的流量
     */
    public int getFlow(int v, int w) {
        if (!networkGraph.hasEdge(v, w)){
            throw new IllegalArgumentException(String.format("顶点%d和顶点%d之间没有边!", v, w));
        }
        // 不能用getWeight(v, w);，因为这个值是流出流量后剩余的权值，反向边的权值即允许逆序流回的流量，其值就是v->w走的流量
        return residualGraph.getWeight(w, v);
    }
}
