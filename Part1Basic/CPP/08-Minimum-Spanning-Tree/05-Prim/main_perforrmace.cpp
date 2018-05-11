/***********************************************************
 * @Description : 读取图的测试
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include <iomanip>
#include <vector>
#include <ctime>
#include <string>
#include "SparseGraph.h"
#include "DenseGraph.h"
#include "ReadGraph.h"
#include "LazyPrimMST.h"
#include "PrimMST.h"

using namespace std;

int main(void) {
    clock_t startTime, endTime;
    string fileName = "testG4.txt";
    int vertices = 10000; // G1:8条边;  G2:250  G3:1000 G4:10000 . 切换不同的测试文件记得这里也要改哈
    cout << fixed << setprecision(2); // 保留2位小数
    cout << "*************************************加权稀疏图************************************" << endl;
    // 1. Lazy的Prim算法
    // false代表为无向图
    SparseGraph<double> sparseGraph = SparseGraph<double>(vertices, false);
    ReadGraph<SparseGraph<double>, double> readSparseGraph(sparseGraph, fileName);
    cout << "Test Lazy Prim MST: " << endl;
    startTime = clock();
    LazyPrimMST<SparseGraph<double>, double> lazyPrimMST(sparseGraph);
    endTime = clock();
    cout<<"cost time: "<<(double)(endTime-startTime)/CLOCKS_PER_SEC<<" s."<<endl;
    cout << endl;
    
    // 2.不Lazy的Prim算法
    cout << "Test Prim MST: " << endl;
    startTime = clock();
    PrimMST<SparseGraph<double>, double> primMST(sparseGraph);
    endTime = clock();
    cout<<"cost time: "<<(double)(endTime-startTime)/CLOCKS_PER_SEC<<" s."<<endl;
    cout << endl;
    return 0;
}
 