/***********************************************************
 * @Description : 读取稀疏、稠密图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/8 07:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#ifndef _READGRAPH_H
#define _READGRAPH_H

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <cassert>
#include "Edge.h"

using namespace std;

// 读取图算法
template<typename Graph,typename Weight>
class ReadGraph {

public:
    // 从文件filename中读取图的信息, 存储进图graph中
    ReadGraph(Graph &graph, const string &filename) {

        ifstream file(filename);
        string line;
        int V, E;

        assert(file.is_open());

        // 第一行读取图中的节点个数和边的个数
        assert(getline(file, line));
        stringstream ss(line);
        ss >> V >> E;

        assert(V == graph.V());

        // 读取每一条边的信息
        for (int i = 0; i < E; i++) {

            assert(getline(file, line));
            stringstream ss(line);

            int a, b;
            Weight weight;
            ss >> a >> b >> weight;
            assert(a >= 0 && a < V);
            assert(b >= 0 && b < V);
            graph.addEdge(a, b, weight);
        }
    }
};

#endif //_READGRAPH_H
