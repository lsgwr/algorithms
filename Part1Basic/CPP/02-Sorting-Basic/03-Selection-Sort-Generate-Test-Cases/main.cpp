/***********************************************************
 * @Description : 2-3 完善单元测试以及通用函数
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 13:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

#include <iostream>
#include "SortTestHelper.h"
using namespace std;
/**
 * 选择排序的核心实现,默认是从小到大进行排序
 * @param arr 待排序的数组
 * @param n   数据的长度(元素的个数)
 */
template<typename T>
void selectionSort(T arr[], int n) {
    for (int i = 0; i < n; ++i) {
        // 寻找[i, n]区间内的最小值
        int minIndex = i;
        // 找出本轮的最小元素
        for (int j = i + 1; j < n; ++j) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        // 把本轮的最小元素的位置和循环的起始位置进行置换
        swap(arr[i], arr[minIndex]);
    }
}

int main() {
    // 测试自定义的算法辅助函数
    int N = 20;
    int *arr = SorterTestHelper::generateRandomArray(N, 0, 100);
    selectionSort(arr, N);
    SorterTestHelper::printArray(arr, N);
    delete arr;
    return 0;
}