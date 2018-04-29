/***********************************************************
 * @Description : 稠密图--邻接矩阵
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 00:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef DENSEGRAPH_H
#define DENSEGRAPH_H

#include <iostream>
#include <vecto>
#include <cassert>

using namespace std;

class DenseGraph {
private:
    // 图的顶点数
    int vertices;
    // 图的边数
    int edges;
    // 当前图是有向图还是无向图
    bool directed;
    // 邻接矩阵，采用vector套vector的形式
    vector <vector<bool>> adj;

public:
    DenseGraph(int vertices, bool directed) {
        this->vertices = vertices;
        this->edges = 0;
        this->directed = directed;
        for (int i = 0; i < vertices; ++i) {
            // 初始化邻接矩阵，每个边连接与否都初始化为false.注意下面的方式省去一层循环了
            adj.push_back(vector<bool>(n, false));
        }
    }

    ~DenseGraph() {

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
        // v和w之间是连接地,不需要再加一次，就直接退出
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = true;
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w][v] = true;
        }
        // 边加1
        edges++;
    }

    // v和w之间是否存在边
    bool hasEdge(int v, int w) {
        // 先确保元素不越界
        assert(v >= 0 && v < vertices);
        assert(w >= 0 && w < vertices);
        return adj[v][w];
    }
};

#endif //DENSEGRAPH_H
