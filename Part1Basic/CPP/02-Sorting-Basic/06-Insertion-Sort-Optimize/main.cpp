/***********************************************************
 * @Description : 2-5 插入排序,因为要交换的次数很多，所以运行速度低于选择排序
 *                插入排序与选择排序的不同：
 *                + 选择排序是从指定位置往后找(后)
 *                + 插入排序是从指定位置往前找(前)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/14 21:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

#include <iostream>
#include "SortTestHelper.h"
using namespace std;
/**
 * 插入排序的核心实现,默认是从小到大进行排序
 * @param arr 待排序的数组
 * @param n   数据的长度(元素的个数)
 */
template<typename T>
void insertionSort(T arr[], int n) {
    // 注意下标从1开始，不停往前比较，下标为0的元素默认有效
    for (int i = 1; i < n; ++i) {
        // 先把起始位置的元素暂存
        T tmp = arr[i];
        // 从位置i开始，从前挨个比较(前面已经是有序地了，这个是前提)，一直比较到前面的某个元素比自己小就停下
        for(int j = i ; j > 0 ; j--){
            if(tmp < arr[j-1]){
                // 后面的元素比前面的小，就把前面的值附到后面来元素上
                arr[j] = arr[j-1];
            }else{
                // 一直到j前面的元素j-1小于tmp了，说明升序排列完成，把之前存的起始位置元素tmp插入到此处即可
                arr[j] = tmp;
                break;
            }
        }

    }
}

int main() {
    // 测试自定义的算法辅助函数
    int N = 10000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    SortTestHelper::testSort("Insertion Sort", insertionSort, arr, N);
    delete[] arr;
    return 0;
}