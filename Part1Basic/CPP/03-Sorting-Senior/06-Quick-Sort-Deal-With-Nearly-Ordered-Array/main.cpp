/***********************************************************
 * @Description : 快速排序的优化：处理接近有序的数组，参考元素需要随意选
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/15 19:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
#include <iostream>
#include "SortTestHelper.h"
using namespace std;
 
// 返回合适的且分点，最终结果如下：返回p，使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p] 
template<typename T> 
int __partition(T arr[], int left, int right){
    swap(arr[left], arr[rand()%(right-left+1) + left]);
    // 选择后面所有元素都要与之比较的元素，一般直接选第一个就好
    T ref = arr[left];
    //开始考察元素： arr[left...j] < v ; arr[j+1...i] > v
    int j = left;
    for(int i = left + 1; i <= right; i++){
        // arr[i] > ref的时候不需要挪动，因为已经在最右侧了
        if(arr[i] < ref){
            // 小于参考元素的都忘左边挪
            swap(arr[j + 1], arr[i]);
            j++;
        }
    }
    // 最后把参考元素置换到中间去
    swap(arr[left], arr[j]);
    // 最后j的位置就是切分点应该在的位置
    return j;
}

// 对arr[left...right]内的元素进行快速排序，需要用到递归
template <typename T>
void __quickSort(T arr[], int left, int right){
    // 左右指针相遇的时候，就直接退出
    if(left >= right){
        return;
    }
    // 选择切分点，执行__partition使得arr[left...p-1] < arr[p] ; arr[p+1...right] > arr[p] 
    int partition = __partition(arr, left, right);
    // 递归进行左右两侧排序
    __quickSort(arr, left, partition-1);
    __quickSort(arr, partition+1, right);
}

template <typename T>
void quickSort(T arr[], int n){
    // 设置随机种子
    srand(time(NULL));
    __quickSort(arr, 0, n-1);
}

int main(){
    // 测试自定义的算法辅助函数
    int N = 1000000;
    int *arr = SortTestHelper::generateRandomArray(N, 0, N);
    // 测试数组是否有序
    SortTestHelper::testSort("Quick Sort", quickSort, arr, N);
    // SortTestHelper::printArray(arr, N);
    delete[] arr;
    return 0;
}