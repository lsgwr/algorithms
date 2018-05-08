/***********************************************************
 * @Description : 稠密图--邻接矩阵
 *                新增：遍历表的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/8 00:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef DENSEGRAPH_H
#define DENSEGRAPH_H

#include <iostream>
#include <vector>
#include <cassert>
#include "Edge.h"

using namespace std;

template<typename Weight>
class DenseGraph {
private:
    // 图的顶点数
    int vertices;
    // 图的边数
    int edges;
    // 当前图是有向图还是无向图
    bool directed;
    // 邻接矩阵，采用vector套vector的形式.注意Edge为指针形式，是因为指针才能为NULL
    vector <vector<Edge<Weight> *> > adj;

public:
    DenseGraph(int vertices, bool directed) {
        this->vertices = vertices;
        this->edges = 0;
        this->directed = directed;
        for (int i = 0; i < vertices; ++i) {
            // 初始化邻接矩阵，每个边连接与否都初始化为NULL.注意下面的方式省去一层循环了
            adj.push_back(vector<Edge<Weight> *>(vertices, NULL));
        }
    }

    ~DenseGraph() {
        // 删除所有节点
        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                // 不为空的节点全部删除
                if(adj[i][j] != NULL){
                    delete adj[i][j];
                }
            }
        }
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
    void addEdge(int v, int w, Weight weight) {
        // 先确保元素不越界
        assert(v >= 0 && v < vertices);
        assert(w >= 0 && w < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出
        if (hasEdge(v, w)) {
            delete adj[v][w];
            // 无向图需要成对删除
            if(!directed){
                delete adj[w][v];
            }
            edges--;
        }
        adj[v][w] = new Edge<Weight>(v, w, weight);
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w][v] = new Edge<Weight>(w, v, weight);
        }
        // 边加1
        edges++;
    }

    // v和w之间是否存在边
    bool hasEdge(int v, int w) {
        // 先确保元素不越界
        assert(v >= 0 && v < vertices);
        assert(w >= 0 && w < vertices);
        return adj[v][w] != NULL;
    }

    // 显示图的信息
    void show(){

        for( int i = 0 ; i < vertices ; i ++ ){
            for( int j = 0 ; j < vertices ; j ++ ){
                if(adj[i][j]){
                    cout<<adj[i][j]->wt()<<"\t";
                }else{
                    cout << "NULL\t";
                }
            }
            cout<<endl;
        }
    }


    // 遍历表的迭代器
    class adjIterator {
    private:
        // 图的引用
        DenseGraph &graph;
        // 指定的顶点
        int v;
        // 当前迭代到哪里
        int index;
    public:
        adjIterator(DenseGraph &graph, int v) : graph(graph) {
            assert(v >= 0 && v < graph.vertices);
            this->v = v;
            this->index = -1;
        }

        Edge<Weight>* begin() {
            index = -1;
            // 找到第一个等于true地，表示起点
            return next();
        }

        Edge<Weight>* next() {
            for (index += 1; index < graph.V(); ++index) {
                // 遇到不为空就返回去
                if (graph.adj[v][index]) {
                    return graph.adj[v][index];
                }
            }
            // 遍历不到返回NULL
            return NULL;
        }

        // 是否已经遍历完了图G中与顶点V相连接的所有顶点
        int end() {
            // 索引打印顶点的个数
            return index >= graph.V();
        }

    };
};

#endif //DENSEGRAPH_H
