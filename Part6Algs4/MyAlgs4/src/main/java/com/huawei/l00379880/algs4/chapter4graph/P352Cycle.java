/***********************************************************
 * @Description : 判断图中是否存在环,环分三种：
 *                  1.自环(一个顶点邻接点是自己)
 *                  2.平行环(两个点之间存在两条一样地边)
 *                  3.常见环(一个顶点经过多个顶点后右回到了自己)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/16 下午11:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;

public class P352Cycle {
    /**
     * marked[v]含义：顶点v是否已经被标记
     */
    private boolean[] marked;
    /**
     * edgeTo[v]:从起点source到顶点v的最短路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 存放环的栈
     */
    private P94LinkedListStack<Integer> cycleStack;

    public P352Cycle(P336Graph graph) {
        if (hasSelfLoop(graph)) {
            return;
        }
        if (hasParallelEdges(graph)) {
            return;
        }
        int V = graph.getV();
        marked = new boolean[V];
        edgeTo = new int[V];
        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                dfs(graph, -1, v);
            }
        }
    }

    /**
     * 判断是否存在自环：邻接点的下标和当前点的下标一样
     */
    private boolean hasSelfLoop(P336Graph graph) {
        for (int v = 0; v < graph.getV(); v++) {
            for (int w : graph.adj(v)) {
                if (v == w) {
                    System.out.println("---------找到自环-----------");
                    cycleStack = new P94LinkedListStack<>();
                    cycleStack.push(v);
                    cycleStack.push(w);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否有两条平行边(v-w之间初始化了两条一样的边）着两条边会形成环)
     *
     * @param graph 图
     * @return 是否存在平行便
     */
    private boolean hasParallelEdges(P336Graph graph) {
        int V = graph.getV();
        marked = new boolean[V];
        for (int v = 0; v < V; v++) {
            for (int w : graph.adj(v)) {
                if (marked[w]) {
                    // 只有v的邻接点中有两次w才会进入到这个分支
                    cycleStack = new P94LinkedListStack<>();
                    // 形成可v-->w-->v的一个环
                    cycleStack.push(v);
                    cycleStack.push(w);
                    cycleStack.push(v);
                }
                marked[w] = true;
            }

            // 重置一下marked[v]为false
            for (int w : graph.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    /**
     * 判断是否有环
     */
    public boolean hasCycle() {
        return cycleStack != null;
    }

    /**
     * 返回找到环
     */
    public Iterable<Integer> getCycle() {
        return cycleStack;
    }

    private void dfs(P336Graph graph, int u, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            // 先判断是否有环(自环和平行环)
            if (hasCycle()) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, v, w);
            } else if (w != u) {
                // 在深度遍历过程中找到了之前标记过的点
                cycleStack = new P94LinkedListStack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycleStack.push(x);
                }
                cycleStack.push(w);
                cycleStack.push(v);
            }
        }
    }
}
