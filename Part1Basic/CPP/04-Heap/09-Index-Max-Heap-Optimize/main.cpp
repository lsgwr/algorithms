#include <iostream>

#include "Heap.h"
#include "SortTestHelper.h"

using namespace std;
// 使用最大索引堆进行堆排序, 来验证我们的最大索引堆的正确性
// 最大索引堆的主要作用不是用于排序, 我们在这里使用排序只是为了验证我们的最大索引堆实现的正确性
// 在后续的图论中, 无论是最小生成树算法, 还是最短路径算法, 我们都需要使用索引堆进行优化:)
template<typename T>
void heapSortUsingIndexMaxHeap(T arr[], int n){

    IndexMaxHeap<T> indexMaxHeap = IndexMaxHeap<T>(n);
    for( int i = 0 ; i < n ; i ++ ){
        indexMaxHeap.insert( arr[i], i );
    }
    // 检查是关键索引数组是否符合顺序
    assert( indexMaxHeap.testIndexesAndReverseIndexes() );

    for( int i = n-1 ; i >= 0 ; i -- ){
        arr[i] = indexMaxHeap.popMax();
    }
}

int main() {

    int n = 1000000;

    int* arr = SortTestHelper::generateRandomArray(n, 0, n);
    SortTestHelper::testSort("Heap Sort Using Index-Max-Heap", heapSortUsingIndexMaxHeap, arr, n);
    delete[] arr;

    return 0;
}