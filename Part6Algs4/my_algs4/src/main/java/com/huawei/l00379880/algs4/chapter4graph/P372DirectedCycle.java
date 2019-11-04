/***********************************************************
 * @Description : 有向图中环的检测
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/19 下午11:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

public class P372DirectedCycle {
    /**
     * marked[v]含义：顶点v是否已经被标记
     */
    private boolean[] marked;
    /**
     * edgeTo[v]:从起点source到顶点v的最短路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 判断顶点是否在栈上
     */
    private boolean[] onStack;
    /**
     * 存放环的栈
     */
    private P94LinkedListStack<Integer> cycleStack;

    public P372DirectedCycle(P366Digraph digraph) {
        int V = digraph.getV();
        marked = new boolean[V];
        onStack = new boolean[V];
        edgeTo = new int[V];
        for (int v = 0; v < V; v++) {
            if (!marked[v] && cycleStack == null) {
                dfs(digraph, v);
            }
        }
    }

    /**
     * check that algorithm computes either the topological order or finds a directed cycle
     *
     * @param digraph 双向图
     * @param v       指定节点
     */
    private void dfs(P366Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (cycleStack != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycleStack = new P94LinkedListStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycleStack.push(x);
                }
                cycleStack.push(w);
                cycleStack.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    public Iterable<Integer> getCycleStack() {
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
            int first = -1;
            int last = -1;
            for (int v : getCycleStack()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begin with %d and ends with %d\n", first, last);
                return false;
            }
        }
        return true;
    }
}
