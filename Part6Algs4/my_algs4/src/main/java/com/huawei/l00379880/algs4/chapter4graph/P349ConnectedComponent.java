/***********************************************************
 * @Description : 找到一个图中的所有连通分量
 *                分无权图和有权图两种，目前仅完成了前者
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/16 下午10:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P349ConnectedComponent {
    /**
     * marked[v]含义：顶点v是否已经被标记
     */
    private boolean[] marked;
    /**
     * id[v]:每条连通路径都会标记上一个id，id[v]=顶点v所属的连通分量的id
     */
    private int[] id;
    /**
     * size[id]:利用id标识的连通分量的顶点的数目
     */
    private int[] size;
    /**
     * 总共的连通分量的个数
     */
    private int count;

    public P349ConnectedComponent(P336Graph graph) {
        int V = graph.getV();
        marked = new boolean[V];
        id = new int[graph.getV()];
        size = new int[graph.getV()];
        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                // 选择不同的起点进行深度优先查找
                dfs(graph, v);
                count++;
            }
        }
    }

    /**
     * 深度优先查找
     *
     * @param graph 待查找到的图
     * @param v     指定的起点
     */
    private void dfs(P336Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                // 把同一条路径上的点标记为同一个id,并把指定的count加1
                dfs(graph, w);
            }
        }
    }

    public int getId(int v) {
        validateVertex(v);
        return id[v];
    }

    public int getSize(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    public int getCount() {
        return count;
    }

    /**
     * 判断两个顶点之间是否相连
     */
    private boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        // 判断两个顶点所属的是不是同一个连通分量
        return id[v] == id[w];
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
}
