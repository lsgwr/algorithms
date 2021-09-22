/***********************************************************
 * @Description : 获取有向图中的强连通分量,和无向图中的连通分量检测一样重要
 *                对应KosarajuSharirSCC.java
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/21 下午9:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P380StronglyConnectedComponent {
    /**
     * marked[v]含义：顶点v是否已经被标记
     */
    private boolean[] marked;
    /**
     * id[v]:每条强连通路径都会标记上一个id，id[v]=顶点v所属的强连通分量的id
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

    public P380StronglyConnectedComponent(P366Digraph digraph) {
        // compute reverse postorder of reverse graph
        P374DepthFirstOrder dfs = new P374DepthFirstOrder(digraph.reverse());
        int V = digraph.getV();
        marked = new boolean[V];
        id = new int[digraph.getV()];
        size = new int[digraph.getV()];
        for (int v : dfs.reversePostOrderQueue()) {
            if (!marked[v]) {
                // 选择不同的起点进行深度优先查找
                dfs(digraph, v);
                count++;
            }
        }
        // 强连通分量的合法性检测
        assert check(digraph);
    }

    /**
     * 检验指定图中顶点对的可达性
     *
     * @param digraph 指定图
     * @return 顶点对可达性是否通过
     */
    private boolean check(P366Digraph digraph) {
        P384TransitiveClosure tc = new P384TransitiveClosure(digraph);
        int V = digraph.getV();
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++) {
                if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 深度优先查找
     *
     * @param digraph 待查找到的图
     * @param v       指定的起点
     */
    private void dfs(P366Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                // 把同一条路径上的点标记为同一个id,并把指定的count加1
                dfs(digraph, w);
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
    private boolean stronglyConnected(int v, int w) {
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
