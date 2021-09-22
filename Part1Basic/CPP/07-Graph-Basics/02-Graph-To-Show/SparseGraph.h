/***********************************************************
 * @Description : 稀疏图---邻接表
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 00:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef SPARSEGRAPH_H
#define SPARSEGRAPH_H

#include <iostream>
#include <vecto>
#include <cassert>

using namespace std;

class SparseGraph {
    // 图的顶点数
    int vertices;
    // 图的边数
    int edges;
    // 当前图是有向图还是无向图
    bool directed;
    // 邻接表，采用vector套vector的形式
    vector <vector<int>> adj;
public:
    SparseGraph(int vertices, bool directed) {
        this->vertices = vertices;
        this->edges = 0;
        this->directed = directed;
        for (int i = 0; i < vertices; ++i) {
            // 初始化邻接表，每个定点对应的邻接表用vector<int>()初始化为空
            adj.push_back(vector<int>());
        }
    }

    ~SparseGraph() {

    }

    // 返回顶点数
    int V() {
        return vertices;
    }

    // 返回边的数目
    int E() {
        return edges;
    }

    // 添加边,在v和w之间建立一条边
    void addEdge(int v, int w) {
        // 先确保元素不越界
        assert(v >= 0 && v < vertices);
        assert(w >= 0 && w < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出。这样是为了防止平行边，但是从hasEdge实现可知成本较高，所以就by了
        // 平行边可以在所有边加完之后统一去掉，自己实现
        // if (hasEdge(v, w)) {
        //    return;
        // }
        adj[v].push_bask(w);
        // v=w会生成自环边
        if (v != w && !directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].push_back(v);
        }
        // 边加1
        edges++;
    }

    // v和w之间是否存在边
    bool hasEdge(int v, int w) {
        // 先确保元素不越界
        assert(v >= 0 && v < vertices);
        assert(w >= 0 && w < vertices);
        for (int i = 0; i < adj[v].size(); ++i) {
            // 判断是否v到w之间存在边
            if (adj[v][i] == w) {
                return true;
            }
        }
        return false;
    }
};

#endif //SPARSEGRAPH_H
