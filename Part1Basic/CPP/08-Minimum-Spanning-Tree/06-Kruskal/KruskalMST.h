#ifndef _KRUSKALMST_H
#define _KRUSKALMST_H

#include <iostream>
#include <vector>
#include "MinHeap.h"
#include "UnionFind.h"
#include "Edge.h"

using namespace std;

template <typename Graph, typename Weight>
class KruskalMST{
private:
    vector<Edge<Weight> > mst;
    Weight mstWeight;

public:
    KruskalMST(Graph &graph){
        // 先对所有的节点采用堆排序
        MinHeap<Edge<Weight> > pq(graph.E());
        for(int i = 0; i < graph.V(); i++){
            // 迭代器，迭代i所对应的临边
            typename Graph::adjIterator adj(graph, i);
            for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
                // 注意无向图一条边会被循环两次，为了避免重复插入,所以额外加一个判断。把edge的两个顶点v和w满足v<w的加入到最小堆中
                if(edge->v()< edge->w()){
                    pq.insert(*edge);
                }
            }
        }
        // 构造并查集
        UF::UnionFind uf = UF::UnionFind(graph.V());
        // 下面是Kruskal算的核心：不断遍历当前所有边中的最小边(minHeap来不断pop),如果这条边加入后会形成环
        // (利用并查集的原理：边的两个短点在并查集中有一个root,则加入这条边一定会形成环)，就跳过，否则加加入mst
        while(!pq.isEmpty() && mst.size()< graph.V()-1){
            // pq不为空而且mst中的边的条数还没收集到v-1条(最小生成树在v个顶点的图中一定要有v-1个点)，就继续找
            Edge<Weight> edge = pq.popMin();
            if(uf.isConnected(edge.v(),edge.w())){
                continue;
            }
            mst.push_back(edge);
            // 没连接过就Union一下
            uf.unionElements(edge.v(),edge.w());
        }
        // 计算最小生成树的权值
        mstWeight = mst[0].wt();
        for(int i = 1; i < mst.size(); i++){
            mstWeight += mst[i].wt();
        }
    }  

    ~KruskalMST(){

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
#endif // !_KRUSKALMST_H
