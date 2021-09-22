/***********************************************************
 * @Description : 读取图的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include <iomanip>
#include "SparseGraph.h"
#include "DenseGraph.h"
#include "ReadGraph.h"

using namespace std;

int main(void) {
    string fileName = "graph1.txt";
    int vertices = 8; // 8条边
    cout << fixed << setprecision(2); // 保留2位小数
    cout << "*************************************加权稀疏图************************************" << endl;
    // 1. 稀疏图
    // false代表为无向图
    SparseGraph<double> sparseGraph = SparseGraph<double>(vertices, false);
    ReadGraph<SparseGraph<double>, double> readSparseGraph(sparseGraph, fileName);
    sparseGraph.show();
    cout << endl;

    cout << "*************************************加权稠密图************************************" << endl;
    // 2.稠密图
    // double代表权重的数据类型，false代表为无向图
    DenseGraph<double> denseGraph = DenseGraph<double>(vertices, false);
    ReadGraph<DenseGraph<double>, double> readDenseGraph(denseGraph, fileName);
    denseGraph.show();
    cout << endl;
    return 0;
}
 