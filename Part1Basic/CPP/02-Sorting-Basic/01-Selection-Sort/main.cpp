/***********************************************************
 * @Description : 2-1 选择排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/13 22:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

#include <iostream>

using namespace std;

/**
 * 选择排序的核心实现,默认是从小到大进行排序
 * @param arr 待排序的数组
 * @param n   数据的长度(元素的个数)
 */
void selectionSort(int arr[], int n) {
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
    int arr[10] = {10, 9, 5, 6, 8, 7, 2, 1, 3, 4};
    selectionSort(arr, 10);
    for (int i = 0; i < 10; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
    return 0;
}