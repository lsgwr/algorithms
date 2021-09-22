/***********************************************************
 * @Description : 寻找指定两点之间的路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 19:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _PATH_H
#define _PATH_H

#include <iostream>
#include <cassert>
#include <stack>
#include <vector>

using namespace std;

template<typename Graph>
class Path {
private:
    Graph &graph;
    // 起始点,后面会求出这个源到其他任意一个节点的路径
    int source;
    bool *visited;
    // from[i]表示访问i节点是从哪一个节点过来地
    int *from;

    // 从v开始遍历连通分量内的所有点
    void dfs(int v) {
        // 上来先把v设置为已访问
        visited[v] = true;
        typename Graph::adjIterator adj(graph, v);
        // 遍历邻接点
        for (int i = adj.begin(); !adj.end(); i = adj.next()) {
            if (!visited[i]) {
                // 访问的i节点是从v节点过来地
                from[i] = v;
                dfs(i);
            }
        }
    }

public:
    Path(Graph &graph, int source) : graph(graph) {
        // 算法初始化
        // 确保source在合适的范围内
        assert(source >= 0 && source < graph.V());
        visited = new bool[graph.V()];
        from = new int[graph.V()];
        for (int i = 0; i < graph.V(); ++i) {
            visited[i] = false;
            from[i] = -1;
        }
        // 设置路径开始的点的坐标
        this->source = source;

        // 寻路算法，稍加改动dfs就行了
        dfs(source);
    }

    ~Path() {
        delete[] visited;
        delete[] from;
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
};

#endif //_PATH_H
