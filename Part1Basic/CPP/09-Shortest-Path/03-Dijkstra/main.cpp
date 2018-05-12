/***********************************************************
 * @Description : 读取图的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include <iomanip>
#include "SparseGraph.h"
#include "ReadGraph.h"
#include "Dijkstra.h"

using namespace std;

int main(void) {
    string fileName = "graph1.txt";
    int vertices = 5; // 5个顶点
    cout << fixed << setprecision(2); // 保留2位小数
    cout << "*************************************加权稀疏图************************************" << endl;
    // true和false 分别代表有向图和无向图。dijkstra算法适用于有向图和无向图
    SparseGraph<int> sparseGraph = SparseGraph<int>(vertices, true);
    ReadGraph<SparseGraph<int>, int> readSparseGraph(sparseGraph, fileName);
    sparseGraph.show();
    cout << "Test Dijkstra:" << endl;
    // s为0，表明计算s到其他所有顶点的最短距离，Dijkstra算法
    Dijkstra<SparseGraph<int>, int> dijkstra(sparseGraph, 0);
    // 从第一个顶点开始断，0->0的最短距离没意义
    for(int i = 1; i< vertices; i++){
        cout << "Shortest Path to " << i << ":" <<endl; 
        cout << "weight is:"<< dijkstra.shortestPathTo(i)<< endl;
        cout << "Path is: ";
        dijkstra.showPath(i);
        cout << "-----------------------------"<< endl;
    }
    return 0;
}
