/***********************************************************
 * @Description : 读取图的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SparseGraph.h"
#include "DenseGraph.h"
#include "ReadGraph.h"
#include "Component.h"

using namespace std;

int main(void) {
    cout << "*************************************稀疏图************************************" << endl;
    // 1. 稀疏图
    string fileName1 = "graph1.txt";
    // false代表为无向图
    SparseGraph sparseGraph(13, false);
    ReadGraph<SparseGraph> readSparseGraph(sparseGraph, fileName1);
    Component<SparseGraph> component1(sparseGraph);
    cout << "line 23" << endl;
    sparseGraph.show();
    cout << "稀疏图的连通分量个数为：" << component1.count() << endl;

    cout << endl;

    cout << "*************************************稠密图************************************" << endl;
    string fileName2 = "graph2.txt";
    // 2.稠密图
    // false代表为无向图
    DenseGraph denseGraph(7, false);
    ReadGraph<DenseGraph> readDenseGraph(denseGraph, fileName2);
    Component<DenseGraph> component2(denseGraph);
    denseGraph.show();
    cout << "稠密图的连通分量个数为：" << component2.count() << endl;
    return 0;
}
 