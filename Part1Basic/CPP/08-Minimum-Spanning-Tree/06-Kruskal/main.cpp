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
#include "LazyPrimMST.h"
#include "PrimMST.h"
#include "KruskalMST.h"

using namespace std;

int main(void) {
    string fileName = "graph1.txt";
    int vertices = 8; // 8条边
    cout << fixed << setprecision(2); // 保留2位小数
    cout << "*************************************加权稀疏图************************************" << endl;
    // 1. Lazy的Prim算法
    // false代表为无向图
    SparseGraph<double> sparseGraph = SparseGraph<double>(vertices, false);
    ReadGraph<SparseGraph<double>, double> readSparseGraph(sparseGraph, fileName);
    sparseGraph.show();
    cout << "Test Lazy Prim MST: " << endl;
    LazyPrimMST<SparseGraph<double>, double> lazyPrimMST(sparseGraph);
    vector<Edge<double> > mst = lazyPrimMST.mstEdges();
    for(int i = 0; i < mst.size(); i++){
        cout << mst[i] << endl;
    }
    cout << "The weight of Lazy Prim MST is : "<<lazyPrimMST.result()<<endl;
    cout << endl;
    
    // 2.不Lazy的Prim算法
    cout << "Test Prim MST: " << endl;
    PrimMST<SparseGraph<double>, double> primMST(sparseGraph);
    mst = primMST.mstEdges();
    for(int i = 0; i < mst.size(); i++){
        cout << mst[i] << endl;
    }
    cout << "The weight of Prim MST is : "<<primMST.result()<<endl;
    
    // 2.Kruskal算法
    cout << "Test Kruskal MST: " << endl;
    KruskalMST<SparseGraph<double>, double> kruskalMST(sparseGraph);
    mst = kruskalMST.mstEdges();
    for(int i = 0; i < mst.size(); i++){
        cout << mst[i] << endl;
    }
    cout << "The weight of Kruskal MST is : "<<kruskalMST.result()<<endl;

    return 0;
}
 