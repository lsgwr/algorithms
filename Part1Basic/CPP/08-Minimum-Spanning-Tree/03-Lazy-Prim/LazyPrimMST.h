/***********************************************************
 * @Description : 最小生成树的Lazy Prim算法实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/10 00:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _LAZY_PRIM_MST_H_
#define _LAZY_PRIM_MST_H_
#include <iostream>
#include <vector>
#include <cassert>
#include "Edge.h"
#include "MinHeap.h"

using namespace std;

template<typename Graph, typename Weight>
class LazyPrimMST{
private:
    Graph &graph;
    // pq:priority queue 优先队列，其实就是最x堆
    MinHeap<Edge<Weight>> pq;
    // 用于标记各个元素是否已经被标记，被标记了说明已经被划分到了另外一个分割区
    bool *marked;
    // 存储组成最小生成树的v-1条边
    vector<Edge<Weight>> mst;
    // 最小生成树的权值.一般是double类型
    Weight mstWeight;
public:
    LazyPrimMST(Graph &graph):graph(graph),pq(MinHeap<Edge<Weight>>(graph.E())){
        // 根据顶点数确定marked的长度
        marked = new bool[graph.V()];
    }

    ~LazyPrimMST(){

    }    
};

#endif // !_LAZY_PRIM_MST_H_

