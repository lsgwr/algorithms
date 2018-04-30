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

using namespace std;

int main(void) {
    string fileName = "graph1.txt";
    cout << "*************************************稀疏图************************************" << endl;
    // 1. 稀疏图
    // false代表为无向图
    SparseGraph sparseGraph(13, false);
    ReadGraph<SparseGraph> readSparseGraph(sparseGraph, fileName);
    sparseGraph.show();

    cout << endl;

    cout << "*************************************稠密图************************************" << endl;
    // 2.稠密图
    // false代表为无向图
    DenseGraph denseGraph(13, false);
    ReadGraph<DenseGraph> readDenseGraph(denseGraph, fileName);
    denseGraph.show();
    return 0;
}
 