/***********************************************************
 * @Description : 09-05 Bellman-Ford算法求最短路径问题,
 *                适用于存在负权边但是没有负权环的图。Bellman适用于有向图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 23：54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

#ifndef _BELLMAN_FORD_H_
#define _BELLMAN_FORD_H_

#include <stack>
#include <vector>
#include "Edge.h"
using namespace std;
template<typename Graph, typename Weight>
class BellmanFord{
private:
    Graph &graph;                 // 图的引用
    int s;                        // 起始点
    Weight* distTo;               // distTo[i]存储从起点s到i的最短路径
    vector<Edge<Weight>* > from;  // from[i]记录最短路径中，到达i点的边是哪一条，可以用来恢复整个最短路径
    bool hasNegativeCycle;        // 标记图中是否有负权环
    
    bool detectNegativeCycle(){
        // 在进行一次松弛操作
        for(int i = 0; i< graph.V(); i++){
            // 迭代器，迭代i所对 应的临边
            typename Graph::adjIterator adj(graph, i);
            for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
                // 如果w没被访问过，或者s先到v再到w比之前直接到w的距离还小，那么就应该更新s->w的距离
                if(!from[edge->w()] || distTo[edge->v()] + edge->wt() < distTo[edge->w()]){
                    // 如果每个点都进行了v-1轮松弛操作了了，还能继续松弛，说明一定有负权环
                    return true;
                }
            }
        }
        return false;
    }
public:
    BellmanFord(Graph &graph, int s):graph(graph){
        this->s = s;
        distTo = new Weight[graph.V()];
        for(int i = 0; i< graph.V(); i++){
            from.push_back(NULL);
        }

        // Bellman-Ford算法核心
        distTo[s] = Weight();
        from[s] = new Edge<Weight>(s, s, Weight()); // 这里我们from[s]的内容是new出来的, 注意要在析构函数里delete掉
        // 进行V-1次循环, 每一次循环求出从起点到其余所有点, 最多使用pass步可到达的最短距离
        // (第一次假设经过一个点就能到目的点，第二次假设经过2个点就能到达目的点.........)
        for(int pass = 1; pass < graph.V(); pass++){
            // 松弛操作
            // 每次循环中对所有的边进行一遍松弛操作
            // 遍历所有边的方式是先遍历所有的顶点, 然后遍历和所有顶点相邻的所有边
            for(int i = 0; i< graph.V(); i++){
                // 迭代器，迭代i所对 应的临边
                typename Graph::adjIterator adj(graph, i);
                for(Edge<Weight>* edge = adj.begin(); !adj.end(); edge = adj.next()){
                    // 对于每一个边首先判断e->v()可达
                    // 之后看如果e->w()以前没有到达过， 显然我们可以更新distTo[e->w()]
                    // 或者e->w()以前虽然到达过, 但是通过这个e我们可以获得一个更短的距离, 即可以进行一次松弛操作, 我们也可以更新distTo[e->w()]
                    if(!from[edge->w()] || distTo[edge->v()] + edge->wt() < distTo[edge->w()]){
                        distTo[edge->w()] = distTo[edge->v()] + edge->wt();
                        from[edge->w()] = edge;
                    }
                }
            }
        }
        hasNegativeCycle = detectNegativeCycle();
    }

    ~BellmanFord(){
        delete[] distTo;
        delete from[s];
    }

    bool negativeCycle(){
        return hasNegativeCycle;
    }

    /**
     * 从起点s到w的最短路径大小
     */
    Weight shortestPathTo(int w){
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert(w >= 0 && w < graph.V());
        // 没有负权环
        assert(!hasNegativeCycle);
        // s到w之间有路径
        assert(hasPathTo(w));
        return distTo[w];
    }  

    /**
     * s到w之间是否有路径
     */
    bool hasPathTo(int w){
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert(w >= 0 && w < graph.V());
        return from[w] != NULL;
    }  

    /**
     * s->w的最短路径上的所有边，从from一各个往回找
     */
    void shortestPath(int w, vector<Edge<Weight> > &vec){
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert(w >= 0 && w < graph.V());
        // 没有负权环
        assert(!hasNegativeCycle);
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
        // 没有负权环
        assert(!hasNegativeCycle);
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
#endif // !_BELLMAN_FORD_H_