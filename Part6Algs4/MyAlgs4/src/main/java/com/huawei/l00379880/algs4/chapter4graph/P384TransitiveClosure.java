/***********************************************************
 * @Description : 计算顶点对的可达性类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/21 下午9:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P384TransitiveClosure {
    /**
     * tc[v] = reachable from v
     */
    private P367DirectedDepthFirstSearch[] tc;

    /**
     * 利用深度优先搜索判断顶点可达性
     *
     * @param digraph
     */
    public P384TransitiveClosure(P366Digraph digraph) {
        int V = digraph.getV();
        tc = new P367DirectedDepthFirstSearch[V];
        for (int v = 0; v < V; v++) {
            tc[v] = new P367DirectedDepthFirstSearch(digraph, v);
        }
    }

    /**
     * Is there a directed path from vertex v to vertex w in the digraph?
     */
    public boolean reachable(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return tc[v].hasPathTo(w);
    }


    /**
     * 校验顶点是否合法
     *
     * @param v 指定顶点
     */
    private void validateVertex(int v) {
        int V = tc.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }
}
