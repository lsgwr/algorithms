/***********************************************************
 * @Description : 深度优先遍历的实现(求连通分量)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 18:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _COMPONENT_H
#define _COMPONENT_H

#include <iostream>
#include <cassert>

using namespace std;

template<typename Graph>
class Component {
private:
    Graph &graph;
    // visited[i]表示i处的点是否已被访问
    bool *visited;
    // 连通分量的个数
    int ccount;

    // 从v开始遍历连通分量内的所有点
    void dfs(int v) {
        // 上来先把v设置为已访问
        visited[v] = true;
        typename Graph::adjIterator adj(graph, v);
        // 遍历邻接点
        for (int i = adj.begin(); !adj.end(); i = adj.next()) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

public:
    Component(Graph &graph) : graph(graph) {
        visited = new bool[graph.V()];
        ccount = 0;
        for (int i = 0; i < graph.V(); ++i) {
            // 初始化的时候每一个点都没有被访问过
            visited[i] = false;
        }
        for (int i = 0; i < graph.V(); ++i) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    ~Component() {
        delete[] visited;
    }

    // 返回连通分量的个数
    int count() {
        return ccount;
    }
};

#endif //_COMPONENT_H
