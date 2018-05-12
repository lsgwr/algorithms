/***********************************************************
 * @Description : 最小生成树的Lazy Prim算法实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/10 00:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _PRIM_MST_H_
#define _PRIM_MST_H_
#include <iostream>
#include <vector>
#include <cassert>
#include "Edge.h"
#include "IndexMinHeap.h"

using namespace std;

template<typename Graph, typename Weight>
class PrimMST{
private:
    Graph &graph;
    // pq:priority queue 优先队列，其实就是最x堆
    IndexMinHeap<Weight> pq;
    // 访问的点所对应的最小横切边
    vector<Edge<Weight>* > edgeTo;
    // 用于标记各个元素是否已经被标记，被标记了说明已经被划分到了另外一个分割区
    bool *marked;
    // 存储组成最小生成树的v-1条边
    vector<Edge<Weight> > mst;
    // 最小生成树的权值.一般是double类型
    Weight mstWeight;
    void visit(int v){
        // 开始先保证v没有被访问过
        assert(!marked[v]);
        marked[v] = true;
        // 将和节点v相连接的未访问的另一端点，和与之相连接的边，放入最小堆中
        typename Graph::adjIterator adj(graph, v);
        for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
            // v在edge边上的另一端的顶点
            int w = edge->other(v);
            // 访问过的都会标成红色，代表已经访问.我们需要未被访问过地
            if(!marked[w]){
                // 如果顶点在求最小横切边的时候被访问过，说明pq中w位置的元素已经存在了，需要更新(update)，否则需要insert
                if(!edgeTo[w]){
                    pq.insert(edge->wt(), w);
                    // 标记下，和w相连地边有了，就是edge
                    edgeTo[w] = edge;
                    
                }else if(edge->wt() < edgeTo[w]->wt()){
                    // 之前标记过而且新的全小于原来的权重就更新
                    pq.update(w, edge->wt());
                    edgeTo[w] = edge;
                }
            }
        }
    } 
public:
    // 相比于LazyPrim算法，下面的graph.E()改成了graph.v()，所以复杂度从O(ElogE)编程了O(ElogV)
    PrimMST(Graph &graph):graph(graph),pq(IndexMinHeap<double >(graph.V())){
        assert(graph.E() >= 1);
        // 根据顶点数确定marked的长度
        marked = new bool[graph.V()];
        for(int i=0; i < graph.V();i++){
            // 初始化每个点都没被访问标记
            marked[i] = false;
            // 每个点的邻切边先设置为NULL
            edgeTo.push_back(NULL);
        }
        // 清空下
        mst.clear();
        //*************** lazy的核心算法 ******************
        // 初始化时从第一个节点开始访问
        visit(0);
        // 优先队列(最小堆)不为空，取出最小值
        while(!pq.isEmpty()){
            int v= pq.popMinIndex();
            // 验证下v有横切边
            assert(edgeTo[v]);
            // 当是横切边的情况下，弹出的最小值一定是最小生成树v-1条边中的一条了，加入到mst中
            mst.push_back(*edgeTo[v]);
            visit(v);
        }

        // 计算最小生成树的权值
        mstWeight = mst[0].wt();
        for(int i = 1; i < mst.size(); i++){
            mstWeight += mst[i].wt();
        }
    }


    ~PrimMST(){
        delete[] marked;
    }   

    // 获取最小生成树的所有的边
    vector<Edge<Weight> > mstEdges(){
        return mst;
    }

    // 获取最小生成树的总权重
    Weight result(){
        return mstWeight;
    }
 
};

#endif // !_PRIM_MST_H_

