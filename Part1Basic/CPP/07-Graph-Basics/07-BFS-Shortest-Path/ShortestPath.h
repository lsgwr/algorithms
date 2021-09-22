/***********************************************************
 * @Description : 广度优先遍历之寻找最短路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 22:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _SHORTESTPATH_H
#define _SHORTESTPATH_H

#include <iostream>
#include <cassert>
#include <stack>
#include <vector>
#include <queue>

using namespace std;

template<typename Graph>
class ShortestPath {
private:
    Graph &graph;
    // 起始点,后面会求出这个源到其他任意一个节点的路径
    int source;
    bool *visited;
    // from[i]表示访问i节点是从哪一个节点过来地
    int *from;
    // source节点到每一个节点的最短距离
    int *order;

public:
    ShortestPath(Graph &graph, int source) : graph(graph) {
        // 算法初始化
        // 确保source在合适的范围内
        assert(source >= 0 && source < graph.V());
        visited = new bool[graph.V()];
        from = new int[graph.V()];
        order = new int[graph.V()];
        for (int i = 0; i < graph.V(); ++i) {
            visited[i] = false;
            from[i] = -1;
            order[i] = -1;
        }
        // 设置路径开始的点的坐标
        this->source = source;

        // 无向图的最短路径算法,类似二叉搜索树的levelOrder(层序遍历，维护一个队列即可)
        queue<int> q;
        q.push(source);
        visited[source] = true;
        // 起点到起点的距离为0
        order[source] = 0;
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            typename Graph::adjIterator adj(graph, v);
            // 遍历邻接点
            for (int i = adj.begin(); !adj.end(); i = adj.next()) {
                if (!visited[i]) {
                    // 元素入队
                    q.push(i);
                    visited[i] = true;
                    // 访问的i节点是从v节点过来地
                    from[i] = v;
                    // 此时边还没有权重
                    order[i] = order[v] + 1;
                }
            }
        }
    }

    ~ShortestPath() {
        delete[] visited;
        delete[] from;
        delete[] order;
    }

    // 起点source到点w之间是否有路径存在
    bool hasPathTo(int w) {
        // 保证w不越界
        assert(w >= 0 && w < graph.V());
        // 访问过说明在一个联通分量内，source肯定是可以和w有条路径连到一起地
        return visited[w];
    }

    // 获取source到w的路径到vec中
    void pathTo(int w, vector<int> &vec) {
        assert(hasPathTo(w));
        // 因为路径是倒推地，所以先压入栈中，然后再弹出到vector中顺序就正常了
        stack<int> pathStack;
        // 路径上的点初始化
        int pathPoint = w;
        // 倒叙查找路径
        while (pathPoint != -1) {
            pathStack.push(pathPoint);
            // 不断往前找
            pathPoint = from[pathPoint];
        }

        vec.clear();
        while (!pathStack.empty()) {
            vec.push_back(pathStack.top());
            pathStack.pop();
        }
    }

    // 打印出source到w的路径
    void showPath(int w) {
        assert(hasPathTo(w));
        vector<int> vec;
        // 把source到w的路径放入vec中
        pathTo(w, vec);
        for (int i = 0; i < vec.size(); ++i) {
            cout << vec[i];
            if (i == vec.size() - 1) {
                // 到达最后一个元素打个回车就行了
                cout << endl;
            } else {
                cout << " -> ";
            }
        }
    }

    // 查询source到w的最短路径的长度
    int length(int w) {
        // 保证w不越界
        assert(w >= 0 && w < graph.V());
        return order[w];
    }
};

#endif //_SHORTESTPATH_H
