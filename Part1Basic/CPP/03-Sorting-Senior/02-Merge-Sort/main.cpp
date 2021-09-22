/***********************************************************
 * @Description : 归并排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 11:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SortTestHelper.h"
using namespace std;

// 对当前轮的元素进行归并:将arr[left...middle]和arr[mid+1...right]两部分进行归并。注意都是闭区间
template<typename T>
void __merge(T arr[], int left, int middle, int right){
    // 声明辅助数组
    T aux[right - left + 1];
    // 元素统一拷贝到指定数组
    for(int i = left; i <= right; i++){
        // 注意数组元素的偏移量是l
        aux[i-left] = arr[i];
    }
    // 开始合并
    // 左侧游标
    int i = left;
    // 右侧游标
    int j = middle + 1;
    // k为要在arr中放入元素的位置
    for(int k = left; k <= right; k++){
        // 先判断特殊情况，一边已经快全部被拿走了，另一边还没动
        if(i > middle){
            // 说明左边的元素排完了，只能用右边地了
            arr[k] = aux[j - left];
            j++;
        }else if(j > right){
            // 右边元素已经排完了，只能用左边的了
            arr[k] = aux[i - left]; 
            i++;
        }else if(aux[i-left] < aux[j-left]){
            // 左半边 < 右半边
            arr[k] = aux[i-left];
            i++;
        }else{
            // 右半边 < 左半边
            arr[k] = aux[j-left];
            j++;
        }
    }

}

// 递归使用归并排序，对arr[left...right]的范围内的元素进行排序，注意左右都是闭区间
template<typename T>
void __mergeSort(T arr[], int left, int right){
    // 如果左侧元素下标大于等于右侧元素下标，说明归并完成，直接退出即可
    if(left >= right ){
        return;
    }
    int middle = (left+right)/2;
    __mergeSort(arr, left, middle);
    __mergeSort(arr, middle+1, right);
    // merge操作
    __merge(arr, left, middle, right);
}

// 最终被调用的函数
template<typename T>
void mergeSort(T arr[], int n){
    // 左逼右闭区间
    __mergeSort(arr, 0, n-1);
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    SortTestHelper::testSort("Merge Sort", mergeSort, arr, N);
    // SortTestHelper::printArray(arr, N);
    delete[] arr;
    return 0;
}