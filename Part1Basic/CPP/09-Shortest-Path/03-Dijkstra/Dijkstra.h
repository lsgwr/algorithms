/***********************************************************
 * @Description : 09-03 Dijkstra算法求最短路径问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 18：40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _DIJKSTRA_H_
#define _DIJKSTRA_H_

#include <iostream>
#include <vector>
#include <stack>
#include "Edge.h"
#include "IndexMinHeap.h"
using namespace std;
template<typename Graph, typename Weight>
class Dijkstra{
private:
    Graph &graph;
    // 起点
    int s;
    Weight *distTo;
    // 找到最短距离的点
    bool *marked;
    // 记录到大当前顶点的前一个顶点
    vector<Edge<Weight>* > from;
public:
    Dijkstra(Graph &graph, int s):graph(graph){
        this->s = s;
        distTo = new Weight[graph.V()];
        marked = new bool[graph.V()];
        for(int i = 0; i < graph.V(); i++){
            distTo[i] = Weight(); // 默认构造函数
            marked[i] = false;  // 上来默认都没被访问过
            from.push_back(NULL); // 起点s没有前一个起点，所以为NULL
        }
        // 初始化最小索引堆
        IndexMinHeap<Weight> pq(graph.V());

        /***** Dijkstra算法的核心实现 *******/
        // s到s的距离为Weight()的默认初始值
        distTo[s] = Weight();
        // 第一个顶点标记下
        marked[s] = true;
        pq.insert(distTo[s], s);
        while(!pq.isEmpty()){
            // 弹出s到所有邻接边中最小的一条所对应的顶点v.v为每一轮的开始点
            int v = pq.popMinIndex();
            // distTo[v]就是s到v的最短距离
            marked[v] = true;
             // 迭代器，迭代i所对应的临边
            typename Graph::adjIterator adj(graph, v);
            for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
                // 获取临边上和v连接的顶点
                int w = edge->other(v);
                // 如果没被标记为已经找到最短距离
                if(!marked[w]){
                    // 如果s->w的距离没有赋过值或者赋过值但是值大于松弛后的值(加一个中转点后的值),那么distTo(w)就需要更新下
                    if(from[w] == NULL || distTo[v] + edge->wt() < distTo[w]){
                        distTo[w] = distTo[v] + edge->wt();
                        // w往前数的第一条边
                        from[w] = edge;
                        if(pq.contain(w)){
                            // 索引有点队列，更新每个点的对应的最短距离
                            pq.update(w, distTo[w]);
                        }else{
                            // 没有计算过最短距离地，第一次要插入下
                            pq.insert(distTo[w], w);
                        }
                    }
                }
            }
        }
    }

    ~Dijkstra(){
        delete[] distTo;
        delete[] marked;
    }

    /**
     * 从起点s到w的最短路径大小
     */
    Weight shortestPathTo(int w){
        return distTo[w];
    }  

    /**
     * s到w之间是否有路径
     */
    bool hasPathTo(int w){
        return marked[w];
    }  

    /**
     * s->w的最短路径上的所有边，从from一各个往回找
     */
    void shortestPath(int w, vector<Edge<Weight> > &vec){
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert(w >= 0 && w < graph.V());
        // s到w之间有路径
        assert(hasPathTo(w));
        stack<Edge<Weight>* > edgeStack;
        // 找到w的上一个顶点
        Edge<Weight>* edge = from[w];
        // 还没找到头(起点s)
        while(edge->v() != this->s){
            // 把当前边加入栈
            edgeStack.push(edge);
            // 接着往前找一条边
            edge = from[edge->v()];
        }
        // 最后反推到第一个点(一般都是起点s，s也要加入到最短路径中)
        edgeStack.push(edge);
        // 逆向从栈中输出路径
        while(!edgeStack.empty()){
            // 获取栈顶元素但是不弹出
            edge =edgeStack.top();
            vec.push_back(*edge);
            // 最后再弹出
            edgeStack.pop();
        }
    }

    /**
     * 完整显示s->w的完整路径
     */
    void showPath(int w){
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert(w >= 0 && w < graph.V());
        // s到w之间有路径
        assert(hasPathTo(w));
        // 计算s->w之间的最短路径
        vector<Edge<Weight> > vec;
        // 计算最短距离
        shortestPath(w, vec);
        for(int i = 0; i < vec.size();i++){
            cout<<vec[i].v()<<"->";
            // 最后一个顶点输出格式要变一下，前面输最后一个边的v点了，这里再输出下最后一条边的另一个点w
            if(i == vec.size()-1){
                cout << vec[i].w() << endl;
            }
        }
    }
};
#endif // !_DIJKSTRA_H_