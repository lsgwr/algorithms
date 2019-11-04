/***********************************************************
 * @Description : 有向图的深度优先搜索，判断两个点之间是否连通.
 *                图的顶点都是用整数顺序标记地
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/19 下午10:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P367DirectedDepthFirstSearch {
    /**
     * 标记顶点是否在连通的路径上
     * marked[v] = is there an s-v path?
     */
    private boolean[] marked;
    /**
     * 连通到s(source)点的顶点数
     */
    private int count;

    /**
     * 计算图graph中所有顶点中连接到下标为source的顶点的个数
     *
     * @param digraph 待查找的图
     * @param source  待查询的点
     */
    public P367DirectedDepthFirstSearch(P366Digraph digraph, int source) {
        marked = new boolean[digraph.getV()];
        validateVertex(source);
        dfs(digraph, source);
    }

    /**
     * 多个点的初始化,获取多个顶点的邻接点集合
     *
     * @param digraph 待查找的双向图
     * @param sources 多个起点
     */
    public P367DirectedDepthFirstSearch(P366Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.getV()];
        validateVertices(sources);
        for (int v : sources) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    /**
     * 通过递归标记出所有和v点连通的点
     *
     * @param digraph 指定图
     * @param v       is other vertexes connected to v?
     */
    private void dfs(P366Digraph digraph, int v) {
        count++;
        marked[v] = true;
        // 这里的循环范围仅限于邻接点,不是所有的点
        // 当没有邻接点或者邻接点都已经标记过的时候就会退出递归
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
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

    public int getCount() {
        return count;
    }
}
