/***********************************************************
 * @Description : 检测有向加权图中是否存在环
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/23 下午11:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

public class P416EdgeWeightedDirectedCycle {
    /**
     * marked[v]含义：顶点v是否已经被标记
     */
    private boolean[] marked;
    /**
     * edgeTo[v]:从起点source到顶点v的最短路径上的最后条路径
     */
    private P415DirectedEdge[] edgeTo;
    /**
     * 判断顶点是否在栈上
     */
    private boolean[] onStack;
    /**
     * 存放环的栈.这里的栈中元素改为有向边对象了
     */
    private P94LinkedListStack<P415DirectedEdge> cycleStack;

    public P416EdgeWeightedDirectedCycle(P415EdgeWeightedDigraph digraph) {
        int V = digraph.getV();
        marked = new boolean[V];
        onStack = new boolean[V];
        edgeTo = new P415DirectedEdge[V];
        for (int v = 0; v < V; v++) {
            if (!marked[v] && cycleStack == null) {
                dfs(digraph, v);
            }
        }

        assert check();
    }

    /**
     * check that algorithm computes either the topological order or finds a directed cycle
     *
     * @param digraph 双向图
     * @param v       指定节点
     */
    private void dfs(P415EdgeWeightedDigraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (P415DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (cycleStack != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = edge;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycleStack = new P94LinkedListStack<>();
                P415DirectedEdge f = edge;
                while (f.from() != w) {
                    cycleStack.push(f);
                    f = edgeTo[f.from()];
                }
                cycleStack.push(f);
                return;
            }
        }
        onStack[v] = false;
    }

    public Iterable<P415DirectedEdge> getCycleStack() {
        return cycleStack;
    }

    public boolean hasCycle() {
        return cycleStack != null;
    }

    /**
     * certify that digraph has a directed cycle if it reports one
     */
    private boolean check() {
        // verify cycle
        if (hasCycle()) {
            P415DirectedEdge first = null;
            P415DirectedEdge last = null;
            for (P415DirectedEdge edge : getCycleStack()) {
                if (first == null) {
                    first = edge;
                }
                if (last != null) {
                    if (last.to() != edge.from()) {
                        System.err.printf("cycle edges %s and %s not incident\n", last, edge);
                    }
                }
                last = edge;
            }
            if (first.from() != last.to()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }
        return true;
    }
}
