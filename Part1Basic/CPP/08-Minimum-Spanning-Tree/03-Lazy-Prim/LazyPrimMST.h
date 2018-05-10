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
    void visit(int v){
        // 开始先保证v没有被访问过
        assert(!marked[v]);
        marked[v] = true;
        typename Graph::adjIterator adj(graph, v);
        for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
            if(!marked[edge.other(v)]){
                // v的没被访问过的邻边(即切分定理中的横切边)加入到最小堆中
                pq.insert(*edge);
            }
        }
    }
public:
    LazyPrimMST(Graph &graph):graph(graph),pq(MinHeap<Edge<Weight>>(graph.E())){
        // 根据顶点数确定marked的长度
        marked = new bool[graph.V()];
        for(int i=0; i < graph.V();i++){
            // 初始化每个点都没被访问标记
            marked[i] = false;
        }
        // 清空下
        mst.clear();
        //*************** lazy的核心算法 ******************
        // 初始化时从第一个节点开始访问
        visit(0);
        // 优先队列(最小堆)不为空，取出最小值
        while(!pq.isEmpty()){
            Edge<Weight> edge = pq.popMin();
            if(marked[edge.v()] == marked[edge.w()]){
                // 弹出来的最小边的两个顶点都已经被访问过了(现在肯定都在横切边的左侧或右侧了)，不能用切分定理了，直接跳过这个点
                continue;
            }
            // 当是横切边的情况下，弹出的最小值一定是最小生成树v-1条边中的一条了，加入到mst中
            mst.push_back(edge);
            // 接着把沿着edge把没访问的点访问起来，邻切边的两个顶点肯定有一个没被访问过
            if(!marked[edge.v()]){
                visit(edge.v());
            }else{
                visit(edge.w());
            }
        }

        // 计算最小生成树的权值
        mstWeight = mst[0].wt();
        for(int i = 1; i < mst.size(); i++){
            mstWeight += mst[i].wt();
        }
    }

    // 获取最小生成树的所有的边
    vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 获取最小生成树的总权重
    Weight result(){
        return mstWeight;
    }

    ~LazyPrimMST(){

    }    
};

#endif // !_LAZY_PRIM_MST_H_

