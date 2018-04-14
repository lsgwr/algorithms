#ifndef _SORTTESTHELPER_H_
#define _SORTTESTHELPER_H_
#include <iostream>
using namespace std;

namespace SorterTestHelper{
    // 生成有n个元素的随即数组，每个元素的随机范围为[rangeL, rangeR]
    int *generateRandomArray(int n, int rangeL, int rangeR){
        assert(rangeL < rangeR);

        int *arr = new int[n];
        srand(time(NULL));
        for(int i=0;i < n; i++){
            arr[i] = rand() % (rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }

    // 打印arr数组的全部内容
    template<typename T>
    void printArray(T arr[], int n){
        for(int i=0; i< n; i++){
            cout << arr[i] << " ";
        }
        cout << endl;
    }
}
#endif // _SORTTESTHELPER_H_