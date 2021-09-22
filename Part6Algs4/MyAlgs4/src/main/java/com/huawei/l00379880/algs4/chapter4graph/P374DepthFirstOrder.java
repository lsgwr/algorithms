/***********************************************************
 * @Description : 有向图中基于深度优先搜索的顶点排序(常用语任务的优先级规划)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/20 上午11:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P94LinkedListStack;
import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

public class P374DepthFirstOrder {
    /**
     * marked[v]=v顶点是否已经在dfs过程中得以标记
     */
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    /**
     * 所有顶点的前序排列
     */
    private P95LinkedListQueue<Integer> preOrderQueue;
    /**
     * 所有顶点的后序排列
     */
    private P95LinkedListQueue<Integer> postOrderQueue;
    private int preCounter;
    private int postCounter;

    public P374DepthFirstOrder(P366Digraph digraph) {
        int V = digraph.getV();
        marked = new boolean[V];
        pre = new int[V];
        post = new int[V];
        preOrderQueue = new P95LinkedListQueue<>();
        postOrderQueue = new P95LinkedListQueue<>();
        for (int v = 0; v < digraph.getV(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
        assert check();
    }

    /**
     * Determines a depth-first order for the edge-weighted digraph {@code G}.
     *
     * @param graph the edge-weighted digraph
     */
    public P374DepthFirstOrder(P415EdgeWeightedDigraph graph) {
        int V = graph.getV();
        marked = new boolean[V];
        pre = new int[V];
        post = new int[V];
        preOrderQueue = new P95LinkedListQueue<>();
        postOrderQueue = new P95LinkedListQueue<>();
        for (int v = 0; v < V; v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    /**
     * 深度优先搜索
     *
     * @param digraph
     * @param v
     */
    private void dfs(P366Digraph digraph, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preOrderQueue.enqueue(v);
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        postOrderQueue.enqueue(v);
        post[v] = postCounter++;
    }

    /**
     * run DFS in edge-weighted digraph G from vertex v and compute preorder/postorder
     */
    private void dfs(P415EdgeWeightedDigraph graph, int v) {
        marked[v] = true;
        pre[v] = preCounter++;
        preOrderQueue.enqueue(v);
        for (P415DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
        postOrderQueue.enqueue(v);
        post[v] = postCounter++;
    }


    public int getPre(int v) {
        validateVertex(v);
        return pre[v];
    }

    public int getPost(int v) {
        validateVertex(v);
        return post[v];
    }

    public Iterable<Integer> getPreOrderQueue() {
        return preOrderQueue;
    }

    public Iterable<Integer> getPostOrderQueue() {
        return postOrderQueue;
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
     * 返回后序的反向
     *
     * @return
     */
    public Iterable<Integer> reversePostOrderQueue() {
        P94LinkedListStack<Integer> reverseStack = new P94LinkedListStack<>();
        for (int v : postOrderQueue) {
            reverseStack.push(v);
        }
        return reverseStack;
    }

    /**
     * check that pre() and post() are consistent with pre(v) and post(v)
     */

    private boolean check() {

        // check that post(v) is consistent with post()
        int r = 0;
        for (int v : getPostOrderQueue()) {
            if (getPost(v) != r) {
                System.out.println("post(v) and post() inconsistent");
                return false;
            }
            r++;
        }

        // check that pre(v) is consistent with pre()
        r = 0;
        for (int v : getPreOrderQueue()) {
            if (getPre(v) != r) {
                System.out.println("pre(v) and pre() inconsistent");
                return false;
            }
            r++;
        }

        return true;
    }
}
