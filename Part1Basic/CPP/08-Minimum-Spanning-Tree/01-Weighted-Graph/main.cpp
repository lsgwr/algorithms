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
#include "Path.h"
#include "ShortestPath.h"

using namespace std;

int main(void) {
    cout << "*************************************稀疏图************************************" << endl;
    // 1. 稀疏图
    string fileName1 = "graph1.txt";
    // false代表为无向图
    SparseGraph sparseGraph(13, false);
    ReadGraph<SparseGraph> readSparseGraph(sparseGraph, fileName1);
    sparseGraph.show();
    cout << endl;
    // 0到所有其他点的路径
    Path<SparseGraph> path1(sparseGraph, 0);
    cout << "DFS path of 0 to 3 :";// 0 -> 5 -> 3
    path1.showPath(3);
    ShortestPath<SparseGraph> path11(sparseGraph, 0);
    cout << "BFS shortest path of 0 to 3 :";// 0 -> 5 -> 3
    path11.showPath(3);


    cout << "*************************************稠密图************************************" << endl;
    string fileName2 = "graph2.txt";
    // 2.稠密图
    // false代表为无向图
    DenseGraph denseGraph(7, false);
    ReadGraph<DenseGraph> readDenseGraph(denseGraph, fileName2);
    denseGraph.show();
    cout << endl;
    // 0到所有其他点的路径
    Path<DenseGraph> path2(denseGraph, 0);
    cout << "DFS path of 0 to 6 :";
    path2.showPath(6);
    ShortestPath<DenseGraph> path22(denseGraph, 0);
    cout << "BFS shortest path of 0 to 6 :";
    path22.showPath(6);
    return 0;
}
 