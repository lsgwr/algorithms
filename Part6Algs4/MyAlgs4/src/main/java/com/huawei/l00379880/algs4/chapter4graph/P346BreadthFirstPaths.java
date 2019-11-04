/***********************************************************
 * @Description : 深度优先搜索(可用于搜索最短路径)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/15 下午8:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

public class P346BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    /**
     * marked[v]：起点source到顶点v是否存在一条路径
     */
    private boolean[] marked;
    /**
     * edgeTo[v]:从起点source到顶点v的最短路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * dist[v]:起点source到顶点v的最短路径的边的条数
     */
    private int[] distTo;

    /**
     * 利用一个现成的图和起点source进行初始化
     *
     * @param graph  一个已有的图
     * @param source 起点
     */
    public P346BreadthFirstPaths(P336Graph graph, int source) {
        // 顶点数目
        int V = graph.getV();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        // 初始化点的距离
        for (int v = 0; v < V; v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(source);
        bfs(graph, source);
        assert check(graph, source);
    }

    /**
     * 计算sources中的任何一个顶点到图中的任何一个顶点的最短路径
     *
     * @param graph   指定的图
     * @param sources 指定的顶点集合
     */
    public P346BreadthFirstPaths(P336Graph graph, Iterable<Integer> sources) {
        // 顶点数目
        int V = graph.getV();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int v = 0; v < V; v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(graph, sources);
    }

    /**
     * 单个起点的广度优先搜索
     *
     * @param graph  待搜寻的图
     * @param source 查找的起点
     */
    private void bfs(P336Graph graph, int source) {
        // 存储未bfs遍历到的顶点，入队后再依次出队进行处理
        P95LinkedListQueue<Integer> queue = new P95LinkedListQueue<>();
        // 到自己的距离最短为0
        distTo[source] = 0;
        // 起点到到自身肯定是最短的
        marked[source] = true;
        // 把起点加入到队列中
        queue.enqueue(source);
        // 循环处理所有的节点
        while (!queue.isEmpty()) {
            // 从队列中出队下一个顶点
            int v = queue.dequeue();
            for (int w : graph.adj(v)) {
                // 先判断顶点w是不是还没被标记,是的话才往下进行
                if (!marked[w]) {
                    // 保存最短路径的最后一条边
                    edgeTo[w] = v;
                    // 距离+1
                    distTo[w] = distTo[v] + 1;
                    // 标记w顶点，因为v到w的最短路径已知
                    marked[w] = true;
                    // 把w顶点加入到队列中，作为下一次轮回的起点
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * 多个起点的广度优先遍历
     *
     * @param graph   待遍历的图
     * @param sources 多个起点
     */
    private void bfs(P336Graph graph, Iterable<Integer> sources) {
        P95LinkedListQueue<Integer> queue = new P95LinkedListQueue<>();
        for (Integer source : sources) {
            marked[source] = true;
            distTo[source] = 0;
            queue.enqueue(source);
        }
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : graph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * 起点source到v点直接是否有一条路径
     *
     * @param v 顶点
     * @return 起点source到v点直接是否有一条路径
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 图的合法性校验
     *
     * @param graph  指定的图
     * @param source 起点
     * @return 是否符合各项要求
     */
    private boolean check(P336Graph graph, int source) {
        // 1.检查起点到起点的距离是否为0
        if (distTo[source] != 0) {
            System.out.println("起点到起点的距离必须为0，现在是：" + distTo[source]);
            return false;
        }

        // 2.在source能够到达v的前提下，检查一条边上的两个点(v-->w)之间的距离是否差为1
        for (int v = 0; v < graph.getV(); v++) {
            for (int w : graph.adj(v)) {
                // source必须能够到达v和w
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("校验一个点到它所有的临界点的距离是否差1出错：" + source + "不能同时到达" + v + "和" + w);
                    System.out.println("edge: " + v + "-->" + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                // 校验source到v的距离和source到w的距离是否只差1
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    System.out.println("校验一个点到它所有的临界点的距离是否差1出错：" + source + "到" + v + "的距离和" + source + "到" + w + "的距离不满足只差1");
                    System.out.println("edge: " + v + "-->" + w);
                    System.out.println("distTo[" + v + "] = " + distTo[v]);
                    System.out.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        // 3.在source到v存在路径的前提下，check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        for (int w = 0; w < graph.getV(); w++) {
            if (!hasPathTo(w) || w == source) {
                continue;
            }
            // 找到source到w的最短路径上的最后一条边v-w
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                System.out.println("校验最短路径上的一条边上的两个点出错：" + source + "到" + v + "的距离和" + source + "到" + w + "的距离不满足只差1");
                System.out.println("最短路径上的一条边: " + v + "-->" + w);
                System.out.println("distTo[" + v + "] = " + distTo[v]);
                System.out.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    /**
     * 校验顶点是否合法
     *
     * @param v 指定顶点
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

    /**
     * 多个顶点的合法性校验
     *
     * @param vertices 多个顶点的可迭代对象
     */
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("入参不能为null");
        }
        int V = marked.length;
        for (Integer v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
            }
        }
    }

    /**
     * 返回source到顶点v之间的最短路径上的边的条数
     *
     * @param v 指定顶点
     * @return 最短路径的边的条数
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * 起点source到顶点v的最短路径
     *
     * @param v 顶点
     * @return 最短路径上的所有顶点下标
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        // 先判断是否有路径
        if (!hasPathTo(v)) {
            return null;
        }
        P94LinkedListStack<Integer> pathStack = new P94LinkedListStack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            pathStack.push(x);
        }
        // 把最后一个节点压栈
        pathStack.push(x);
        return pathStack;
    }

}
