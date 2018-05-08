/***********************************************************
 * @Description : 稀疏图---邻接表
 *                新增：遍历表的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 00:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef SPARSEGRAPH_H
#define SPARSEGRAPH_H

#include <iostream>
#include <vector>
#include <cassert>
#include "Edge.h"

using namespace std;
template<typename Weight>
class SparseGraph {
    // 图的顶点数
    int vertices;
    // 图的边数
    int edges;
    // 当前图是有向图还是无向图
    bool directed;
    // 邻接表，采用vector套vector的形式
    vector <vector<Edge<Weight> *> > adj;
public:
    SparseGraph(int vertices, bool directed) {
        this->vertices = vertices;
        this->edges = 0;
        this->directed = directed;
        for (int i = 0; i < vertices; ++i) {
            // 初始化邻接表，每个定点对应的邻接表用vector<Edge<Weight> *>()初始化为空
            adj.push_back(vector<Edge<Weight> *>());
        }
    }

    ~SparseGraph() {
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
        // v和w之间是连接地,不需要再加一次，就直接退出。这样是为了防止平行边，但是从hasEdge实现可知成本较高，所以就by了
        // 平行边可以在所有边加完之后统一去掉，自己实现
        // if (hasEdge(v, w)) {
        //    return;
        // }
        adj[v].push_back(new Edge<Weight>(v, w, weight));
        // v=w会生成自环边
        if (v != w && !directed) {
            // 无向图实际上是双向图，所以w到v也应该为Edge.如果是有向图这步就不用处理了
            adj[w].push_back(new Edge<Weight>(w, v, weight));
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
            if (adj[v][i]->other(v) == w) {
                return true;
            }
        }
        return false;
    }

    // 显示图的信息,邻接表
    void show(){

        for( int i = 0 ; i < vertices ; i ++ ){
            cout<<"vertex "<<i<<":\t";
            for( int j = 0 ; j < adj[i].size() ; j ++ )
                cout << "( to" << adj[i][j]->w() << ", weight: " << adj[i][j]->wt()<<")\t";
            cout<<endl;
        }
    }

    // 遍历表的迭代器
    class adjIterator {
    private:
        // 图的引用
        SparseGraph &graph;
        // 指定的顶点
        int v;
        // 当前迭代到哪里
        int index;
    public:
        adjIterator(SparseGraph &graph, int v) : graph(graph) {
            this->v = v;
            this->index = 0;
        }

        ~adjIterator() {

        }

        Edge<Weight>* begin() {
            index = 0;
            // 确实有元素的话,就返回第一个元素的值
            if (graph.adj[v].size() != 0) {
                return graph.adj[v][index];
            }
            return NULL;
        }

        Edge<Weight>* next() {
            index++;
            // 先判断是否越界
            if (index < graph.adj[v].size()) {
                return graph.adj[v][index];
            }
            return NULL;
        }

        int end() {
            return index >= graph.adj[v].size();
        }

    };
};

#endif //SPARSEGRAPH_H
