/***********************************************************
 * @Description : 环检测
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020-4-22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.Chapter04DFSInAction.S09环检测;

import com.huawei.l00379880.Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFS无向图环检测拿到所有简单环 {
    private Graph graph;
    private boolean[] visited;
    private List<Integer> order;
    private List<List<Integer>> cycles;

    public GraphDFS无向图环检测拿到所有简单环(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        order = new ArrayList<>();
        cycles = new ArrayList<>();
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    // 如果找到了环，当前遍历的order就是环，再把起点加进去就行录入
                    order.add(v);
                    cycles.add(new ArrayList<>(order));
                }
                // 没轮DFS都记得清空下列表
                order = new ArrayList<>();
            }
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        order.add(v);
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                return dfs(w, v);
            } else {
                // 节点w已经被访问，但是w并不是v的上一个节点
                if (w != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<List<Integer>> getCycles() {
        return cycles;
    }
}
