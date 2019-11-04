package com.huawei.l00379880.algs4.chapter4graph;


import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

/***********************************************************
 * @Description : 利用深度优先搜索找出指定两点之间的全部路径
 * @author      : 梁山广
 * @date        : 2018/2/14 17:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P343DepthFirstPaths {
    /**
     * marked[v]:source顶点到指定顶点v之间是否存在一条路径
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = last edge on s-v path
     */
    private int[] edgeTo;
    /**
     * 起始顶点
     */
    private final int source;

    public P343DepthFirstPaths(P336Graph graph, int source) {
        this.source = source;
        // 顶点数目
        int V = graph.getV();
        edgeTo = new int[V];
        marked = new boolean[V];
        validateVertex(source);
        dfs(graph, source);
    }

    /**
     * 通过递归标记出所有和v点连通的点
     *
     * @param graph 指定图
     * @param v     is other vertexes connected to v?
     */
    private void dfs(P336Graph graph, int v) {
        marked[v] = true;
        // 这里的循环范围仅限于邻接点(初始化图的时候就给出了),不是所有的点
        // 当没有邻接点或者邻接点都已经标记过的时候就会退出递归
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                // 连接上一条路径
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    /**
     * 判断定点编号是否在合理范围内[0~V)
     *
     * @param v 顶点编号
     */
    private void validateVertex(int v) {
        // 顶点的总数目
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }

    /**
     * 判断在起点source和指定顶点V之间是否有一条连通路径
     *
     * @param v 待检查的点
     * @return source和v之间是否有一条连通路径
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 返回可迭代的路径数组(此处为Stack类型)
     *
     * @param v source要连接的点
     * @return source到v的路径
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        P94LinkedListStack<Integer> pathStack = new P94LinkedListStack<>();
        for (int x = v; x != source; x = edgeTo[x]) {
            pathStack.push(x);
        }
        pathStack.push(source);
        return pathStack;
    }


}
