/***********************************************************
 * @Description : 深度优先遍历的实现(求连通分量).
 *                新增：利用并查集来判断两点之间是否连接
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 19:08
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
    int *id;

    // 从v开始遍历连通分量内的所有点
    void dfs(int v) {
        // 上来先把v设置为已访问
        visited[v] = true;
        // 同一个连通分量内，其parent(即为id[i]的值)设置为当前连通分量的顺序号即可
        id[v] = ccount;
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
        id = new int[graph.V()];
        ccount = 0;
        for (int i = 0; i < graph.V(); ++i) {
            // 初始化的时候每一个点都没有被访问过
            visited[i] = false;
            id[i] = -1;
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
        delete[] id;
    }

    // 返回连通分量的个数
    int count() {
        return ccount;
    }

    bool isConnected(int v, int w) {
        // 检测元素是否过界
        assert(v >= 0 && v < graph.V());
        assert(w >= 0 && w < graph.V());
        return id[v] == id[w];
    }
};

#endif //_COMPONENT_H
