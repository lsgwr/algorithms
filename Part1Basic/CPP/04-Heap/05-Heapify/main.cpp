#include <iostream>
#include "Heap.h"
#include "SortTestHelper.h"
using namespace std;

// 挨个插入元素构成堆
template<typename T>
void heapSort(T arr[], int n){
    MaxHeap<T> maxHeap = MaxHeap<T>(n);
    for(int i = 0; i < n; i++ ){
        maxHeap.insert(arr[i]);
    }
    while(!maxHeap.isEmpty()){
        maxHeap.popMax();
    }
    cout << "堆排序完成！" <<endl;
}

// 一次性利用数组在构造函数中生成堆
template<typename T>
void heapSort2(T arr[], int n){
    MaxHeap<T> maxHeap = MaxHeap<T>(arr, n);
    while(!maxHeap.isEmpty()){
        maxHeap.popMax();
    }
    cout << "堆排序完成！" <<endl;
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    //  SortTestHelper::testSort("Heap Sort", heapSort, arr, N);
     SortTestHelper::testSort("Heap Sort with Array", heapSort2, arr, N);
    delete[] arr;
    return 0;
}