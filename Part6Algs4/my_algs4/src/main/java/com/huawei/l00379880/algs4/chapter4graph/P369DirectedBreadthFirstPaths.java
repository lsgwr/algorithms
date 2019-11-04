/***********************************************************
 * @Description : 有向图的广度优先搜索
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/19 下午11:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

public class P369DirectedBreadthFirstPaths {
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
     * @param digraph 一个已有的图
     * @param source  起点
     */
    public P369DirectedBreadthFirstPaths(P366Digraph digraph, int source) {
        // 顶点数目
        int V = digraph.getV();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        // 初始化点的距离
        for (int v = 0; v < V; v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(source);
        bfs(digraph, source);
//        assert check(digraph, source);
    }

    /**
     * 计算sources中的任何一个顶点到图中的任何一个顶点的最短路径
     *
     * @param digraph 指定的图
     * @param sources 指定的顶点集合
     */
    public P369DirectedBreadthFirstPaths(P366Digraph digraph, Iterable<Integer> sources) {
        // 顶点数目
        int V = digraph.getV();
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        for (int v = 0; v < V; v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(digraph, sources);
    }

    /**
     * 单个起点的广度优先搜索
     *
     * @param digraph 待搜寻的图
     * @param source  查找的起点
     */
    private void bfs(P366Digraph digraph, int source) {
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
            for (int w : digraph.adj(v)) {
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
     * @param digraph 待遍历的图
     * @param sources 多个起点
     */
    private void bfs(P366Digraph digraph, Iterable<Integer> sources) {
        P95LinkedListQueue<Integer> queue = new P95LinkedListQueue<>();
        for (Integer source : sources) {
            marked[source] = true;
            distTo[source] = 0;
            queue.enqueue(source);
        }
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : digraph.adj(v)) {
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
