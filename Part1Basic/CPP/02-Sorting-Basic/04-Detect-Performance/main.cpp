/***********************************************************
 * @Description : 2-4 测试算法的性能
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 19:59
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
    int N = 100000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 岑石数组是否有序
    SortTestHelper::testSort("Selection Sort", selectionSort, arr, N);
    delete[] arr;
    return 0;
}